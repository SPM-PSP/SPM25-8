<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div class="page-header">
          <h2 class="page-title">AI 智能助手</h2>
          <div class="page-desc">智能问答服务</div>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :xs="24" :sm="24" :md="20" :lg="18" :xl="16" class="chat-container">
        <div class="message-panel" id="message-panel">
          <div v-if="messageList.length === 0" class="empty-state">
            <i class="el-icon-chat-line-round"></i>
            <p>开始您的第一次对话</p>
          </div>
          <div v-else class="message-list">
            <div
              :class="['message-item', item.type == 1 ? 'ai-item' : '']"
              v-for="(item, index) in messageList"
              :key="index"
              :id="'item' + index"
            >
              <template v-if="item.type == 0">
                <div class="message-content">
                  <div class="content-inner">{{ item.content }}</div>
                </div>
                <div class="user-icon">我</div>
              </template>
              <template v-else>
                <div class="user-icon">AI</div>
                <div class="message-content ai-item">
                  <div class="ai-response" v-html="formatResponse(item.content.join(''))"></div>
                  <div class="loading" v-if="item.loading">
                    <i class="el-icon-loading"></i>
                  </div>
                </div>
              </template>
            </div>
          </div>
        </div>
        
        <div class="send-panel">
          <el-row :gutter="10">
            <el-col :span="24">
              <el-form :model="formData" ref="formDataRef" @submit.native.prevent class="input-form">
                <el-form-item class="model-select">
                  <el-select size="small" placeholder="选择模型" v-model="formData.model">
                    <el-option value="4.0Ultra" label="科大讯飞星火大模型"></el-option>
                  </el-select>
                </el-form-item>
                
                <el-form-item class="input-area">
                  <el-input
                    type="textarea"
                    :rows="3"
                    placeholder="请输入您的问题，按Ctrl+Enter快速发送"
                    v-model="formData.content"
                    @keyup.native="keySend"
                  ></el-input>
                </el-form-item>
                
                <el-form-item class="send-btn">
                  <el-button type="primary" @click="sendMessage" :disabled="loading">
                    <i class="el-icon-s-promotion"></i> 发送
                  </el-button>
                </el-form-item>
              </el-form>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "AIAssistant",
  data() {
    return {
      formData: {
        model: "4.0Ultra",
        content: ""
      },
      messageList: [],
      loading: false
    };
  },
  methods: {
    keySend(event) {
      if (!(event.ctrlKey && event.key === "Enter")) {
        return;
      }
      this.sendMessage();
    },
    sendMessage() {
      const message = this.formData.content;
      if (!message) {
        this.$message({
          type: "warning",
          message: "请输入内容",
          duration: 2000
        });
        return;
      }
      this.messageList.push({
        type: 0,
        content: message
      });

      this.messageList.push({
        type: 1,
        content: [],
        loading: true
      });
      this.loading = true;

      // 使用绝对路径并指定正确的端口号21090
      const apiUrl = `http://localhost:21090/api/personal-heath/v1.0/ai-assistant/stream?model=${
        this.formData.model
      }&message=${encodeURIComponent(message)}`;
      
      console.log("正在连接流式接口:", apiUrl);
      
      this.formData.content = "";
      
      try {
        // 使用EventSource建立SSE连接
        const eventSource = new EventSource(apiUrl);
        
        // 处理接收到的消息
        eventSource.onmessage = (event) => {
          let response = event.data;
          console.log("接收到SSE消息:", response);
          
          // 收到结束标记时关闭连接
          if (response === "end") {
            this.closeEventSource(eventSource);
            return;
          }
          
          try {
            // 解析JSON响应
            const parsedResponse = JSON.parse(response);
            if (parsedResponse && parsedResponse.content) {
              this.messageList[this.messageList.length - 1].content.push(parsedResponse.content);
              
              // 滚动到底部
              this.$nextTick(() => {
                const content = document.getElementById("message-panel");
                if (content) {
                  content.scrollTop = content.scrollHeight;
                }
              });
            }
          } catch (parseError) {
            console.error("解析SSE消息失败:", parseError);
          }
        };
        
        // 处理连接错误
        eventSource.onerror = (error) => {
          console.error("SSE连接错误:", error);
          this.closeEventSource(eventSource);
          this.$message.error("AI助手连接异常，请稍后再试");
        };
        
        // 15秒后如果还没收到响应，主动关闭连接（防止卡死）
        this.connectionTimeout = setTimeout(() => {
          if (this.loading) {
            console.warn("SSE连接超时，主动关闭");
            this.closeEventSource(eventSource);
            this.$message.warning("AI响应超时，请重试");
          }
        }, 15000);
        
      } catch (error) {
        console.error("创建EventSource失败:", error);
        this.loading = false;
        this.messageList[this.messageList.length - 1].loading = false;
        this.$message.error("无法连接到AI服务，请检查网络连接");
      }
    },
    
    closeEventSource(eventSource) {
      if (eventSource) {
        eventSource.close();
      }
      
      // 清除超时计时器
      if (this.connectionTimeout) {
        clearTimeout(this.connectionTimeout);
        this.connectionTimeout = null;
      }
      
      this.messageList[this.messageList.length - 1].loading = false;
      this.loading = false;
    },
    formatResponse(text) {
      if (!text) return '';
      
      // Replace newlines with <br>
      let formatted = text.replace(/\n/g, '<br>');
      
      // Highlight code blocks
      formatted = formatted.replace(/```([\s\S]*?)```/g, '<pre class="code-block">$1</pre>');
      
      // Highlight inline code
      formatted = formatted.replace(/`([^`]+)`/g, '<code>$1</code>');
      
      return formatted;
    }
  }
};
</script>

<style scoped lang="scss">
.page-header {
  padding: 15px 0;
  margin-bottom: 20px;
  
  .page-title {
    font-size: 24px;
    margin: 0;
    padding: 0;
    color: #333;
  }
  
  .page-desc {
    color: #666;
    font-size: 14px;
    margin-top: 5px;
  }
}

.chat-container {
  margin: 0 auto;
  background: #fff;
  border-radius: 6px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
  padding: 0;
  overflow: hidden;
}

.message-panel {
  position: relative;
  height: calc(100vh - 300px);
  min-height: 400px;
  overflow-y: auto;
  padding: 20px;
  background-color: #f8f9fa;
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
  
  .empty-state {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    color: #909399;
    
    i {
      font-size: 48px;
      margin-bottom: 10px;
    }
    
    p {
      font-size: 16px;
    }
  }
  
  .message-list {
    .message-item {
      margin: 15px 0;
      display: flex;
      
      .user-icon {
        width: 40px;
        height: 40px;
        line-height: 40px;
        flex-shrink: 0;
        border-radius: 50%;
        background: #409EFF;
        color: #fff;
        text-align: center;
        font-size: 14px;
        margin-left: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      }
      
      .message-content {
        flex: 1;
        margin-left: 10px;
        display: flex;
        justify-content: flex-end;
      }
      
      .content-inner {
        background: #409EFF;
        padding: 12px 16px;
        border-radius: 8px 0 8px 8px;
        color: #fff;
        max-width: 85%;
        font-size: 14px;
        line-height: 1.5;
        word-break: break-word;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
      }
    }
    
    .ai-item {
      .user-icon {
        background: #67C23A;
        margin-left: 0;
        margin-right: 10px;
      }
      
      .message-content {
        justify-content: flex-start;
        margin-left: 0;
        margin-right: 10px;
      }
      
      .ai-response {
        background: #fff;
        color: #333;
        padding: 12px 16px;
        border-radius: 0 8px 8px 8px;
        font-size: 14px;
        line-height: 1.6;
        width: 100%;
        box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
        border: 1px solid #EBEEF5;
      }
      
      .loading {
        text-align: center;
        margin-top: 8px;
        
        i {
          font-size: 20px;
          color: #67C23A;
        }
      }
    }
  }
}

.ai-response >>> .code-block, 
.ai-response >>> pre {
  background-color: #f8f8f8;
  border-radius: 4px;
  padding: 10px;
  margin: 8px 0;
  overflow-x: auto;
  font-family: Consolas, Monaco, 'Andale Mono', monospace;
  font-size: 13px;
  line-height: 1.4;
  border: 1px solid #e9e9e9;
  color: #333;
}

.ai-response >>> code {
  background-color: #f0f0f0;
  padding: 2px 4px;
  border-radius: 3px;
  font-family: Consolas, Monaco, 'Andale Mono', monospace;
  font-size: 13px;
  color: #d63384;
}

.send-panel {
  padding: 15px;
  border-top: 1px solid #EBEEF5;
  background: #fff;
  
  .input-form {
    display: flex;
    flex-direction: column;
    
    .model-select {
      margin-bottom: 10px;
      
      .el-select {
        width: 230px;
      }
    }
    
    .input-area {
      margin-bottom: 10px;
      
      .el-textarea {
        .el-textarea__inner {
          border-color: #DCDFE6;
          transition: all 0.2s;
          
          &:focus {
            border-color: #409EFF;
            box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.2);
          }
        }
      }
    }
    
    .send-btn {
      display: flex;
      justify-content: flex-end;
      margin-bottom: 0;
      
      .el-button {
        padding: 10px 20px;
      }
    }
  }
}

// 响应式调整
@media (max-width: 768px) {
  .message-panel {
    height: calc(100vh - 280px);
    padding: 10px;
  }
  
  .send-panel {
    padding: 10px;
    
    .input-form {
      .model-select {
        .el-select {
          width: 100%;
        }
      }
    }
  }
  
  .message-list .message-item {
    .content-inner, .ai-response {
      max-width: 80%;
    }
  }
}
</style> 