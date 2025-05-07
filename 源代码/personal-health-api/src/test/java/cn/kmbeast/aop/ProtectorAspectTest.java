package cn.kmbeast.aop;

import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProtectorAspectTest {

    @InjectMocks
    private ProtectorAspect protectorAspect;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private MethodSignature methodSignature;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAuthWithNoToken() throws Throwable {
        // Set up mock request with no token
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);

        // Call the aspect method
        Object result = protectorAspect.auth(joinPoint);

        // Verify the method returns error result
        assertTrue(result instanceof Result);
        Result<?> apiResult = (Result<?>) result;
        assertEquals(400, apiResult.getCode());
        assertEquals("身份认证失败，请先登录", apiResult.getMsg());

        // Verify the joinPoint.proceed() was not called
        verify(joinPoint, never()).proceed();
    }

    @Test
    void testAuthWithValidToken() throws Throwable {
        // Create a valid token
        String token = JwtUtil.toToken(1, 1);

        // Set up mock request with token
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("token", token);
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);

        // Mock the method signature and annotation
        when(joinPoint.getSignature()).thenReturn(methodSignature);

        // Get a real method with the Protector annotation for testing
        Method method = findMethodWithProtectorAnnotation();
        when(methodSignature.getMethod()).thenReturn(method);

        // Mock joinPoint.proceed() to return a success result
        Result<String> expectedResult = ApiResult.success("Operation successful");
        when(joinPoint.proceed()).thenReturn(expectedResult);

        // Call the aspect method
        Object result = protectorAspect.auth(joinPoint);

        // Verify the result is the same as what joinPoint.proceed() returned
        assertSame(expectedResult, result);

        // Verify joinPoint.proceed() was called
        verify(joinPoint, times(1)).proceed();
    }

    // Helper method to find a method with @Protector annotation for testing
    private Method findMethodWithProtectorAnnotation() throws NoSuchMethodException {
        // Create a test class with a @Protector annotated method
        class TestController {
            @Protector(role = "管理员")
            public void protectedMethod() {}
        }

        return TestController.class.getMethod("protectedMethod");
    }

    @Test
    void testAuthWithInvalidRole() throws Throwable {
        // Create a token with a role that does NOT match "管理员"
        String token = JwtUtil.toToken(1, 2); // 2 = 普通用户，不是“管理员”

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("token", token);
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);

        when(joinPoint.getSignature()).thenReturn(methodSignature);
        Method method = findMethodWithProtectorAnnotation(); // 里面写的是 @Protector(role = "管理员")
        when(methodSignature.getMethod()).thenReturn(method);

        Object result = protectorAspect.auth(joinPoint);

        assertTrue(result instanceof Result);
        Result<?> apiResult = (Result<?>) result;
        assertEquals(400, apiResult.getCode());
        assertEquals("无操作权限", apiResult.getMsg());

        verify(joinPoint, never()).proceed();
    }

}