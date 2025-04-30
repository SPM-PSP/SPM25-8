package cn.kmbeast.controller;

import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.UserQueryDto;
import cn.kmbeast.pojo.dto.update.UserLoginDTO;
import cn.kmbeast.pojo.dto.update.UserRegisterDTO;
import cn.kmbeast.pojo.dto.update.UserUpdateDTO;
import cn.kmbeast.pojo.entity.User;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.pojo.vo.UserVO;
import cn.kmbeast.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testUserLogin() throws Exception {
        // 创建一个空的LoginDTO对象
        // 由于使用了Jackson序列化，不需要设置字段也可以测试
        UserLoginDTO loginDTO = new UserLoginDTO();
        
        // 创建一个ApiResult而不是Result，因为需要设置data字段
        ApiResult<Object> successResult = new ApiResult<>(200, "登录成功", "token123", null);
        
        // Mock service response
        when(userService.login(any(UserLoginDTO.class))).thenReturn(successResult);
        
        // 使用空对象进行测试，或者创建一个简单的JSON字符串
        String loginJson = "{\"userAccount\":\"testuser\",\"userPwd\":\"password\"}";
        
        // Perform API call and verify
        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("登录成功"))
                .andExpect(jsonPath("$.data").value("token123"));
    }

    @Test
    void testRegisterUser() throws Exception {
        // 由于使用了Jackson序列化，不需要设置字段也可以测试
        UserRegisterDTO registerDTO = new UserRegisterDTO();
        
        // 创建一个ApiResult而不是Result
        ApiResult<String> successResult = new ApiResult<>(200, "注册成功");
        
        // Mock service response
        when(userService.register(any(UserRegisterDTO.class))).thenReturn(successResult);
        
        // 使用简单的JSON字符串代替对象
        String registerJson = "{\"userName\":\"newuser\",\"userAccount\":\"newaccount\",\"userPwd\":\"password\"}";
        
        // Perform API call and verify
        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("注册成功"));
    }

    @Test
    void testUpdatePassword() throws Exception {
        // Prepare test data
        Map<String, String> pwdMap = new HashMap<>();
        pwdMap.put("oldPwd", "oldpwd");
        pwdMap.put("newPwd", "newpwd");
        
        // 创建一个ApiResult而不是Result
        ApiResult<String> successResult = new ApiResult<>(200, "密码修改成功");
        
        // Mock service response
        when(userService.updatePwd(any())).thenReturn(successResult);
        
        // Perform API call and verify
        mockMvc.perform(put("/user/updatePwd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pwdMap)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("密码修改成功"));
    }
    
    @Test
    void testAuth() throws Exception {
        // 创建用户VO对象
        UserVO userVO = new UserVO();
        // 使用适当的方法或直接设置字段
        // userVO.setId(1);
        // userVO.setUserName("测试用户");
        // userVO.setUserAccount("testaccount");
        
        // 模拟服务层返回
        ApiResult<UserVO> successResult = new ApiResult<>(200, "认证成功", userVO);
        when(userService.auth()).thenReturn(successResult);
        
        // 执行请求并验证
        mockMvc.perform(get("/user/auth")
                .header("token", "valid-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("认证成功"));
    }
    
    @Test
    void testGetById() throws Exception {
        // 创建用户VO对象
        UserVO userVO = new UserVO();
        // 使用适当的方法或直接设置字段
        // userVO.setId(1);
        // userVO.setUserName("测试用户");
        // userVO.setUserAccount("testaccount");
        
        // 模拟服务层返回
        ApiResult<UserVO> successResult = new ApiResult<>(200, "获取成功", userVO);
        when(userService.getById(anyInt())).thenReturn(successResult);
        
        // 执行请求并验证
        mockMvc.perform(get("/user/getById/1")
                .header("token", "valid-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("获取成功"));
    }
    
    @Test
    void testInsert() throws Exception {
        // 创建注册DTO
        UserRegisterDTO registerDTO = new UserRegisterDTO();
        // 使用适当的方法或直接设置字段
        // registerDTO.setUserName("newadmin");
        // registerDTO.setUserAccount("adminaccount");
        // registerDTO.setUserPwd("adminpass");
        
        // 模拟服务层返回
        ApiResult<String> successResult = new ApiResult<>(200, "添加成功");
        when(userService.insert(any(UserRegisterDTO.class))).thenReturn(successResult);
        
        // 执行请求并验证
        String registerJson = "{\"userName\":\"newadmin\",\"userAccount\":\"adminaccount\",\"userPwd\":\"adminpass\"}";
        mockMvc.perform(post("/user/insert")
                .header("token", "admin-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("添加成功"));
    }
    
    @Test
    void testUpdate() throws Exception {
        // 创建更新DTO
        UserUpdateDTO updateDTO = new UserUpdateDTO();
        // 注意：UserUpdateDTO没有id字段，所以我们不再设置
        // 使用适当的方法或直接设置字段
        // updateDTO.setUserName("更新用户名");
        // updateDTO.setUserEmail("updated@example.com");
        
        // 模拟服务层返回
        ApiResult<String> successResult = new ApiResult<>(200, "更新成功");
        when(userService.update(any(UserUpdateDTO.class))).thenReturn(successResult);
        
        // 执行请求并验证
        String updateJson = "{\"userAccount\":\"testaccount\",\"userName\":\"更新用户名\",\"userEmail\":\"updated@example.com\"}";
        mockMvc.perform(put("/user/update")
                .header("token", "valid-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("更新成功"));
    }
    
    @Test
    void testBackUpdate() throws Exception {
        // 创建用户对象
        User user = new User();
        // 使用适当的方法或直接设置字段
        // user.setId(1);
        // user.setUserName("后台更新");
        // user.setIsLogin(false);
        // user.setIsWord(false);
        
        // 模拟服务层返回
        ApiResult<String> successResult = new ApiResult<>(200, "后台更新成功");
        when(userService.backUpdate(any(User.class))).thenReturn(successResult);
        
        // 执行请求并验证
        String userJson = "{\"id\":1,\"userName\":\"后台更新\",\"isLogin\":false,\"isWord\":false}";
        mockMvc.perform(put("/user/backUpdate")
                .header("token", "admin-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("后台更新成功"));
    }
    
    @Test
    void testBatchDelete() throws Exception {
        // 创建ID列表
        List<Integer> ids = Arrays.asList(1, 2, 3);
        
        // 模拟服务层返回
        ApiResult<String> successResult = new ApiResult<>(200, "批量删除成功");
        when(userService.batchDelete(anyList())).thenReturn(successResult);
        
        // 执行请求并验证
        mockMvc.perform(post("/user/batchDelete")
                .header("token", "admin-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ids)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("批量删除成功"));
    }
    
    @Test
    void testQuery() throws Exception {
        // 创建查询DTO
        UserQueryDto queryDto = new UserQueryDto();
        // 直接在JSON中设置字段，避免使用可能不存在的setter方法
        String queryJson = "{\"current\":1,\"size\":10}";
        
        // 创建用户列表
        List<User> users = new ArrayList<>();
        User user1 = new User();
        // 使用适当的方法设置属性或创建有适当属性的JSON
        users.add(user1);
        
        User user2 = new User();
        // 使用适当的方法设置属性
        users.add(user2);
        
        // 模拟服务层返回 - 使用带有total参数的构造函数
        ApiResult<List<User>> successResult = new ApiResult<>(200, "查询成功", users, 2);
        when(userService.query(any(UserQueryDto.class))).thenReturn(successResult);
        
        // 执行请求并验证
        mockMvc.perform(post("/user/query")
                .header("token", "admin-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(queryJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("查询成功"));
    }
    
    @Test
    void testDaysQuery() throws Exception {
        // 创建图表数据 - 使用反射或JSON直接创建测试数据
        List<ChartVO> chartData = new ArrayList<>();
        // 假设ChartVO有name和count字段，但没有对应的构造函数
        
        // 模拟服务层返回
        ApiResult<List<ChartVO>> successResult = new ApiResult<>(200, "统计成功", chartData);
        when(userService.daysQuery(anyInt())).thenReturn(successResult);
        
        // 执行请求并验证
        mockMvc.perform(get("/user/daysQuery/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("统计成功"));
    }
} 