package cn.kmbeast.service;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.UserMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.update.UserLoginDTO;
import cn.kmbeast.pojo.dto.update.UserRegisterDTO;
import cn.kmbeast.pojo.entity.User;
import cn.kmbeast.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUserAccount("chenhao");
        loginDTO.setUserPwd("14e1b600b1fd579f47433b88e8d85291");

        User user = User.builder()
                .id(13)
                .userAccount("chenhao")
                .userName("陈浩")
                .userPwd("14e1b600b1fd579f47433b88e8d85291")
<<<<<<< HEAD
                .userRole(2)
=======
                .userRole(2) // 假设USER角色是1
>>>>>>> ab6b53d14d8c3a670e9fcfea60a87c06ded06bed
                .isLogin(false)
                .build();

        when(userMapper.getByActive(any(User.class))).thenReturn(user);

        Result<Object> result = userService.login(loginDTO);

        assertEquals(200, result.getCode());
        assertEquals("登录成功", result.getMsg());
        assertNotNull(((ApiResult<Object>) result).getData());
    }

    @Test
    void testUserLogin_AccountNotExist() {
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUserAccount("nonexist");
        loginDTO.setUserPwd("somepassword");

        when(userMapper.getByActive(any(User.class))).thenReturn(null);

        Result<Object> result = userService.login(loginDTO);

        assertEquals(400, result.getCode());
        assertEquals("账号不存在", result.getMsg());
    }

    @Test
    void testRegisterUser_Success() {
        UserRegisterDTO registerDTO = new UserRegisterDTO();
        registerDTO.setUserName("testuser");
        registerDTO.setUserAccount("testaccount");
        registerDTO.setUserPwd("testpassword");

        when(userMapper.getByActive(any(User.class))).thenReturn(null);

        Result<String> result = userService.register(registerDTO);

        assertEquals(200, result.getCode());
        assertEquals("注册成功", result.getMsg());

        verify(userMapper, times(1)).insert(any(User.class));
    }

    @Test
    void testRegisterUser_DuplicateUsername() {
        UserRegisterDTO registerDTO = new UserRegisterDTO();
        registerDTO.setUserName("existingname");
        registerDTO.setUserAccount("existingaccount");
        registerDTO.setUserPwd("password");

        User existingUser = User.builder()
                .id(1)
                .userName("existingname")
                .build();

        when(userMapper.getByActive(any(User.class))).thenReturn(existingUser);

        Result<String> result = userService.register(registerDTO);

        assertEquals(400, result.getCode());
        assertEquals("用户名已经被使用，请换一个", result.getMsg());

        verify(userMapper, never()).insert(any(User.class));
    }

    @Test
    void testUpdatePassword_Success() {
        Map<String, String> pwdMap = new HashMap<>();
<<<<<<< HEAD
        pwdMap.put("oldPwd", "oldpassword");
        pwdMap.put("newPwd", "newpassword");

        User user = User.builder()
                .id(1)
                .userPwd("oldpassword")
                .userRole(1)
                .build();

        LocalThreadHolder.setUserId(user.getId(), user.getUserRole());

        when(userMapper.getByActive(any(User.class))).thenReturn(user);

        Result<String> result = userService.updatePwd(pwdMap);

        assertEquals(200, result.getCode());
        verify(userMapper, times(1)).update(any(User.class));
=======
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
>>>>>>> ab6b53d14d8c3a670e9fcfea60a87c06ded06bed
    }
}
