package cn.kmbeast.controller;

import cn.kmbeast.pojo.ai.AiResult;
import cn.kmbeast.pojo.ai.ContentDto;
import cn.kmbeast.utils.JsonUtils;
import okhttp3.*;
import okhttp3.internal.sse.RealEventSource;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/ai-assistant")
@CrossOrigin(origins = "*")
public class AiController {
    private static final Logger logger = LoggerFactory.getLogger(AiController.class);

    private static final String DONE = "[DONE]";
    private static final Integer timeout = 60;

    private static final String AI_URL = "https://spark-api-open.xf-yun.com/v1/chat/completions";

    private static final String MODEL_ULTRA = "4.0Ultra";

    @Value("${api.password:}")
    private String apiPassword;

    /**
     * 处理AI流式响应的接口
     * 使用Server-Sent Events (SSE)技术向前端实时返回AI生成的内容
     * 
     * 前端通过EventSource对象连接此接口，接收实时数据流
     * 通信流程:
     * 1. 前端创建EventSource对象连接到此接口(/stream)
     * 2. 后端设置响应头为text/event-stream
     * 3. 后端调用AI API获取流式响应
     * 4. 每接收到一段AI内容，包装成ContentDto后发送给前端
     * 5. 前端通过onmessage事件接收每段消息并更新UI
     * 6. 发送"end"标记通知前端AI回答结束
     * 
     * @param model AI模型名称
     * @param message 用户输入的消息
     * @param response HTTP响应对象
     */
    @GetMapping(value = "/stream")
    public void handleSse(String model, String message, HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");
        response.setHeader("Access-Control-Allow-Origin", "*");
        
        logger.error("收到AI请求 - 模型: {}, 消息内容: {}", model, message);
        
        try (PrintWriter pw = response.getWriter()) {
            getAiResult4Ultra(pw, message);
            logger.info("AI响应完成，发送结束标记");
            pw.write("data:end\n\n");
            pw.flush();
        } catch (IOException e) {
            logger.error("IO异常: ", e);
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            logger.error("线程中断异常: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 调用科大讯飞API获取AI响应
     * 使用OkHttp的EventSource实现SSE流式接收
     * 
     * 数据流处理:
     * 1. 使用RealEventSource建立与科大讯飞API的连接
     * 2. 通过EventSourceListener接收流式响应
     * 3. 每接收到一段数据，解析并发送给前端
     * 4. 前端格式: data:{"content":"AI回答内容片段"}\n\n
     * 
     * @param pw 响应输出流
     * @param content 用户输入内容
     * @throws InterruptedException 线程中断时抛出
     */
    private void getAiResult4Ultra(PrintWriter pw, String content) throws InterruptedException {
        Map<String, Object> params = new HashMap<>();
        params.put("model", "4.0Ultra");

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", content);

        List<Map> messages = new ArrayList<>();
        messages.add(message);
        params.put("messages", messages);
        params.put("stream", true);
        String jsonParams = JsonUtils.convertObj2Json(params);
        logger.info("请求参数: {}", jsonParams);

        Request.Builder builder = new Request.Builder().url(AI_URL);
        builder.addHeader("Authorization", " Bearer " + apiPassword);
        builder.addHeader("Accept", "text/event-stream");
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = builder.post(body).build();
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(timeout, TimeUnit.SECONDS).writeTimeout(timeout, TimeUnit.SECONDS).readTimeout(timeout,
                TimeUnit.SECONDS).build();

        // 实例化EventSource，注册EventSource监听器 -- 创建一个用于处理服务器发送事件的实例，并定义处理事件的回调逻辑
        CountDownLatch eventLatch = new CountDownLatch(1);

        RealEventSource realEventSource = new RealEventSource(request, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                if (DONE.equals(data)) {
                    return;
                }
                String content = getContent(data);
                logger.debug("接收到AI响应内容: {}", content);
                pw.write("data:" + JsonUtils.convertObj2Json(new ContentDto(content)) + "\n\n");
                pw.flush();
            }

            @Override
            public void onClosed(EventSource eventSource) {
                super.onClosed(eventSource);
                eventLatch.countDown();
            }

            @Override
            public void onFailure(EventSource eventSource, Throwable t, Response response) {
                logger.error("调用科大讯飞接口失败: {}", t.getMessage(), t);
                if (eventLatch != null) {
                    eventLatch.countDown();
                }
            }
        });
        // 与服务器建立连接
        realEventSource.connect(client);
        // await() 方法被调用来阻塞当前线程，直到 CountDownLatch 的计数变为0。
        eventLatch.await();
    }

    /**
     * 解析科大讯飞API返回的JSON数据，提取内容
     * 
     * @param data 原始JSON数据
     * @return 提取的内容文本
     */
    private static String getContent(String data) {
        logger.debug("解析AI响应数据: {}", data);
        AiResult aiResult = JsonUtils.convertJson2Obj(data, AiResult.class);
        String content = aiResult.getChoices().get(0).getDelta().getContent();
        return content;
    }
}
