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
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.dto.query.extend.UserQueryDto;
import cn.kmbeast.pojo.dto.update.UserUpdateDTO;
import cn.kmbeast.pojo.vo.UserVO;

import java.time.LocalDateTime;
import java.util.*;

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
                .userRole(2)
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

        pwdMap.put("oldPwd", "123456");
        pwdMap.put("newPwd", "12345");

        // 使用新变量名避免冲突
        User anotherUser = User.builder()
                .id(1)
                .userPwd("123456")
                .build();

        when(userMapper.getByActive(any(User.class))).thenReturn(anotherUser);

        // 调用服务方法
        Result<String> result2 = userService.updatePwd(pwdMap);

        // 验证结果
        assertEquals(200, result2.getCode());

        // 验证 update 方法被调用两次
        verify(userMapper, times(2)).update(any(User.class));
    }

    @Test
    void testAuth_Success() {
        Integer userId = 123;
        LocalThreadHolder.setUserId(userId,1);

        User mockUser = User.builder().id(userId).userName("TestUser").build();
        when(userMapper.getByActive(any(User.class))).thenReturn(mockUser);

        Result<UserVO> result = userService.auth();

        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMsg().intern());
    }

    @Test
    void testQueryUsers_ReturnsList() {
        List<User> mockUsers = Arrays.asList(
                User.builder().id(1).userName("A").build(),
                User.builder().id(2).userName("B").build()
        );

        when(userMapper.query(any(UserQueryDto.class))).thenReturn(mockUsers);
        when(userMapper.queryCount(any(UserQueryDto.class))).thenReturn(mockUsers.size());

        Result<List<User>> result = userService.query(new UserQueryDto());

        assertEquals(200, result.getCode());
        assertEquals(2, ((PageResult<List<User>>) result).getData().size());
    }

    @Test
    void testUpdateUserInfo_Success() {
        LocalThreadHolder.setUserId(99,1);
        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setUserName("UpdatedName");

        Result<String> result = userService.update(dto);

        assertEquals(200, result.getCode());
        verify(userMapper, times(1)).update(any(User.class));
    }

    @Test
    void testBatchDelete_Success() {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        Result<String> result = userService.batchDelete(ids);

        assertEquals(200, result.getCode());
        verify(userMapper, times(1)).batchDelete(ids);
    }

    @Test
    void testUpdatePassword_Failure_WrongOldPwd() {
        LocalThreadHolder.setUserId(99,1);
        Map<String, String> pwdMap = new HashMap<>();
        pwdMap.put("oldPwd", "wrong");
        pwdMap.put("newPwd", "new123");

        User mockUser = User.builder().id(1).userPwd("correct").build();
        when(userMapper.getByActive(any(User.class))).thenReturn(mockUser);

        Result<String> result = userService.updatePwd(pwdMap);

        assertEquals(400, result.getCode());
        assertEquals("原始密码验证失败", result.getMsg());
    }


    @Test
    void testGetUserById() {
        User mockUser = User.builder().id(1).userName("ChenHao").build();
        when(userMapper.getByActive(any(User.class))).thenReturn(mockUser);

        Result<UserVO> result = userService.getById(1);

        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMsg().intern());
    }

    @Test
    void testInsertUser_Backend() {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUserName("backendUser");
        dto.setUserAccount("backendAcc");

        when(userMapper.getByActive(any(User.class))).thenReturn(null);

        Result<String> result = userService.insert(dto);

        assertEquals(200, result.getCode());
        verify(userMapper, times(1)).insert(any(User.class));
    }

    @Test
    void testBackUpdateUser() {
        User user = User.builder().id(1).userName("BackUpdate").build();

        Result<String> result = userService.backUpdate(user);

        assertEquals(200, result.getCode());
        verify(userMapper, times(1)).update(user);
    }

    @Test
    void testDaysQuery() {
        List<User> mockList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mockList.add(User.builder().createTime(LocalDateTime.now().minusDays(i)).build());
        }
        when(userMapper.query(any(UserQueryDto.class))).thenReturn(mockList);

        Result<?> result = userService.daysQuery(7);

        assertEquals(200, result.getCode());
        assertNotNull(result.getMsg());
    }

    @Test
    void testLogin_Failure_WrongPassword() {
        UserLoginDTO dto = new UserLoginDTO();
        dto.setUserAccount("testUser");
        dto.setUserPwd("wrongPwd");

        User dbUser = User.builder().userAccount("testUser").userPwd("correctPwd").build();

        when(userMapper.getByActive(any(User.class))).thenReturn(dbUser);

        Result<?> result = userService.login(dto);

        assertEquals(400, result.getCode());
        assertEquals("密码错误", result.getMsg());
    }

    @Test
    void testLogin_Failure_AlreadyLoggedIn() {
        UserLoginDTO dto = new UserLoginDTO();
        dto.setUserAccount("testUser");
        dto.setUserPwd("correctPwd");

        User dbUser = User.builder()
                .userAccount("testUser")
                .userPwd("correctPwd")
                .isLogin(true) // 用户已登录
                .build();

        when(userMapper.getByActive(any(User.class))).thenReturn(dbUser);

        Result<?> result = userService.login(dto);

        assertEquals(400, result.getCode());
        assertEquals("登录状态异常", result.getMsg());
    }

}