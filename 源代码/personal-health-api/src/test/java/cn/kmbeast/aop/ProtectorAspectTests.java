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
public class ProtectorAspectTests {

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
            @Protector
            public void protectedMethod() {}
        }
        
        return TestController.class.getMethod("protectedMethod");
    }
    
    @Test
    void testAuthWithInvalidRole() throws Throwable {
        // Create a token with role 1 (assuming role 1 is not an admin)
        String token = JwtUtil.toToken(1, 1);
        
        // Set up mock request with token
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("token", token);
        ServletRequestAttributes attributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attributes);
        
        // Mock the method signature
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        
        // Mock the Protector annotation that requires admin role
        Protector mockProtector = mock(Protector.class);
        when(mockProtector.role()).thenReturn("管理员");
        
        // Get a method and set the mock annotation
        Method method = findMethodWithProtectorAnnotation();
        when(methodSignature.getMethod()).thenReturn(method);
        when(method.getAnnotation(Protector.class)).thenReturn(mockProtector);
        
        // Call the aspect method
        Object result = protectorAspect.auth(joinPoint);
        
        // Verify the method returns error result
        assertTrue(result instanceof Result);
        Result<?> apiResult = (Result<?>) result;
        assertEquals(400, apiResult.getCode());
        assertEquals("无操作权限", apiResult.getMsg());
        
        // Verify joinPoint.proceed() was not called
        verify(joinPoint, never()).proceed();
    }
} 