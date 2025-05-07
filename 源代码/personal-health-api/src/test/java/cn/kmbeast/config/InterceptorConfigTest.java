package cn.kmbeast.config;

import cn.kmbeast.Interceptor.JwtInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  // JUnit 5 使用 Mockito 扩展
class InterceptorConfigTest {  // 测试类可以是包私有

    private InterceptorConfig interceptorConfig;

    @Mock
    private InterceptorRegistry mockRegistry;

    @Mock
    private InterceptorRegistration mockRegistration;

    @BeforeEach
    void setUp() throws Exception {  // 使用 @BeforeEach 替代 @Before
        interceptorConfig = new InterceptorConfig();

        // 使用反射设置私有字段 API 的值
        Field apiField = InterceptorConfig.class.getDeclaredField("API");
        apiField.setAccessible(true);
        apiField.set(interceptorConfig, "/api/v1");

        // 配置 mockRegistry 行为
        when(mockRegistry.addInterceptor(any(JwtInterceptor.class))).thenReturn(mockRegistration);
    }

    @Test
    void testAddInterceptors() {  // 测试方法可以是包私有
        interceptorConfig.addInterceptors(mockRegistry);

        // 验证拦截器注册和路径配置
        verify(mockRegistry).addInterceptor(any(JwtInterceptor.class));
        verify(mockRegistration).addPathPatterns("/**");
        verify(mockRegistration).excludePathPatterns(
                "/api/v1/user/login",
                "/api/v1/user/register",
                "/api/v1/file/upload",
                "/api/v1/file/getFile",
                "/api/v1/ai-assistant/stream"
        );
    }
}