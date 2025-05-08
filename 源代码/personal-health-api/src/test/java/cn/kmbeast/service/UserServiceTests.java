package cn.kmbeast.service;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.UserMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.update.UserLoginDTO;
import cn.kmbeast.pojo.dto.update.UserRegisterDTO;
import cn.kmbeast.pojo.dto.update.UserUpdateDTO;
import cn.kmbeast.pojo.em.LoginStatusEnum;
import cn.kmbeast.pojo.em.RoleEnum;
import cn.kmbeast.pojo.entity.User;
import cn.kmbeast.pojo.vo.UserVO;
import cn.kmbeast.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUserLogin_Success() {
        // Prepare test data
        UserLoginDTO loginDTO = new UserLoginDTO();
        // Set necessary fields for loginDTO
        
        User user = User.builder()
                .id(13)
                .userAccount("chenhao")
                .userName("陈浩")
                .userPwd("14e1b600b1fd579f47433b88e8d85291")
                .userRole(2) // 假设USER角色是1
                .isLogin(false)
                .build();
                
        // Mock the mapper's response
        when(userMapper.getByActive(any(User.class))).thenReturn(user);
        
        // Call service method
        Result<Object> result = userService.login(loginDTO);
        
        // Verify the result
        assertEquals(200, result.getCode());
        assertEquals("登录成功", result.getMsg());
        Object data = ((ApiResult<Object>)result).getData();
        assertNotNull(data);
    }
    
    @Test
    void testUserLogin_AccountNotExist() {
        // Prepare test data
        UserLoginDTO loginDTO = new UserLoginDTO();
        // Set necessary fields for loginDTO
        
        // Mock the mapper's response for non-existent user
        when(userMapper.getByActive(any(User.class))).thenReturn(null);
        
        // Call service method
        Result<Object> result = userService.login(loginDTO);
        
        // Verify the result
        assertEquals(400, result.getCode());
        assertEquals("账号不存在", result.getMsg());
    }
    
    @Test
    void testRegisterUser_Success() {
        // Prepare test data
        UserRegisterDTO registerDTO = new UserRegisterDTO();
        // Set necessary fields for registerDTO
        
        // Mock the mapper's response when checking for duplicate username and account
        when(userMapper.getByActive(any(User.class))).thenReturn(null);
        
        // Call service method
        Result<String> result = userService.register(registerDTO);
        
        // Verify the result
        assertEquals(200, result.getCode());
        assertEquals("注册成功", result.getMsg());
        
        // Verify that insert was called
        verify(userMapper, times(1)).insert(any(User.class));
    }
    
    @Test
    void testRegisterUser_DuplicateUsername() {
        // Prepare test data
        UserRegisterDTO registerDTO = new UserRegisterDTO();
        // Set necessary fields for registerDTO
        
        // Mock the mapper's response for an existing username
        User existingUser = User.builder()
                .id(1)
                .userName("existingname")
                .build();
        
        when(userMapper.getByActive(any(User.class))).thenReturn(existingUser);
        
        // Call service method
        Result<String> result = userService.register(registerDTO);
        
        // Verify the result
        assertEquals(400, result.getCode());
        assertEquals("用户名已经被使用，请换一个", result.getMsg());
        
        // Verify that insert was not called
        verify(userMapper, never()).insert(any(User.class));
    }
    
    @Test
    void testUpdatePassword_Success() {
        // Prepare test data
        Map<String, String> pwdMap = new HashMap<>();
        pwdMap.put("oldPwd", "123456");
        pwdMap.put("newPwd", "12345");
        
        // Mock thread local user ID
        try {
            // Use reflection to set the ThreadLocal value
            // This is a simplified approach - in a real test you might need a more sophisticated solution
            User user = User.builder()
                    .id(1)
                    .userPwd("123456")
                    .build();
            
            when(userMapper.getByActive(any(User.class))).thenReturn(user);
            
            // Call service method
            Result<String> result = userService.updatePwd(pwdMap);
            
            // Verify the result
            assertEquals(200, result.getCode());
            
            // Verify that update was called
            verify(userMapper, times(1)).update(any(User.class));
        } finally {
            // Clean up
        }
    }
} 