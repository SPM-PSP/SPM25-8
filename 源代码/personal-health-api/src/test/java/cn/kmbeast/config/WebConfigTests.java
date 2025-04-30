package cn.kmbeast.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class WebConfigTests {

    @Autowired
    private WebConfig webConfig;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void testWebConfigImplementsWebMvcConfigurer() {
        // Test that WebConfig implements WebMvcConfigurer
        assertTrue(webConfig instanceof WebMvcConfigurer);
    }

    @Test
    void testCorsConfigurationSet() throws Exception {
        // Set up MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // Test CORS configuration with a preflight request
        mockMvc.perform(options("/api/test")
                .header("Origin", "http://example.com")
                .header("Access-Control-Request-Method", "GET")
                .header("Access-Control-Request-Headers", "Content-Type"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Access-Control-Allow-Origin"))
                .andExpect(header().exists("Access-Control-Allow-Methods"))
                .andExpect(header().exists("Access-Control-Allow-Headers"));
    }
    
    @Test
    void testAddCorsMappingsMethod() throws Exception {
        // 简化测试，只检查WebConfig是否覆盖了WebMvcConfigurer接口的方法
        Class<?> webConfigClass = WebConfig.class;
        Class<?> webMvcConfigInterface = WebMvcConfigurer.class;
        
        // 确认WebConfig类实现了WebMvcConfigurer接口
        assertTrue(webMvcConfigInterface.isAssignableFrom(webConfigClass));
        
        // 试图获取addCorsMappings方法
        try {
            Method method = webConfigClass.getDeclaredMethod("addCorsMappings", CorsRegistry.class);
            assertNotNull(method, "addCorsMappings方法应该存在");
        } catch (NoSuchMethodException e) {
            fail("WebConfig应该实现addCorsMappings方法");
        }
    }
} 