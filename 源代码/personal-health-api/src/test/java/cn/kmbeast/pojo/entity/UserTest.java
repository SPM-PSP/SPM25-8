package cn.kmbeast.pojo.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * User实体类测试
 */
class UserTest {

    @Test
    void testUserProperties() {
        // 测试默认构造函数
        User user = new User();
        assertNotNull(user);
        
        try {
            // 使用反射设置和获取属性
            // ID
            Field idField = User.class.getDeclaredField("id");
            idField.setAccessible(true);
            Integer id = 1;
            idField.set(user, id);
            assertEquals(id, idField.get(user));
            
            // UserAccount
            Field userAccountField = User.class.getDeclaredField("userAccount");
            userAccountField.setAccessible(true);
            String userAccount = "testaccount";
            userAccountField.set(user, userAccount);
            assertEquals(userAccount, userAccountField.get(user));
            
            // UserName
            Field userNameField = User.class.getDeclaredField("userName");
            userNameField.setAccessible(true);
            String userName = "测试用户";
            userNameField.set(user, userName);
            assertEquals(userName, userNameField.get(user));
            
            // UserPwd
            Field userPwdField = User.class.getDeclaredField("userPwd");
            userPwdField.setAccessible(true);
            String userPwd = "password123";
            userPwdField.set(user, userPwd);
            assertEquals(userPwd, userPwdField.get(user));
            
            // UserAvatar
            Field userAvatarField = User.class.getDeclaredField("userAvatar");
            userAvatarField.setAccessible(true);
            String userAvatar = "avatar.jpg";
            userAvatarField.set(user, userAvatar);
            assertEquals(userAvatar, userAvatarField.get(user));
            
            // UserEmail
            Field userEmailField = User.class.getDeclaredField("userEmail");
            userEmailField.setAccessible(true);
            String userEmail = "test@example.com";
            userEmailField.set(user, userEmail);
            assertEquals(userEmail, userEmailField.get(user));
            
            // UserRole
            Field userRoleField = User.class.getDeclaredField("userRole");
            userRoleField.setAccessible(true);
            Integer userRole = 0;
            userRoleField.set(user, userRole);
            assertEquals(userRole, userRoleField.get(user));
            
            // IsLogin
            Field isLoginField = User.class.getDeclaredField("isLogin");
            isLoginField.setAccessible(true);
            Boolean isLogin = true;
            isLoginField.set(user, isLogin);
            assertEquals(isLogin, isLoginField.get(user));
            
            // IsWord
            Field isWordField = User.class.getDeclaredField("isWord");
            isWordField.setAccessible(true);
            Boolean isWord = false;
            isWordField.set(user, isWord);
            assertEquals(isWord, isWordField.get(user));
            
            // CreateTime
            Field createTimeField = User.class.getDeclaredField("createTime");
            createTimeField.setAccessible(true);
            LocalDateTime createTime = LocalDateTime.now();
            createTimeField.set(user, createTime);
            assertEquals(createTime, createTimeField.get(user));
        } catch (Exception e) {
            fail("反射操作失败: " + e.getMessage());
        }
    }
    
    @Test
    void testUserBuilder() {
        // 测试Builder模式构造
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .id(1)
                .userAccount("testaccount")
                .userName("测试用户")
                .userPwd("password123")
                .userAvatar("avatar.jpg")
                .userEmail("test@example.com")
                .userRole(0)
                .isLogin(true)
                .isWord(false)
                .createTime(now)
                .build();
        
        // 验证所有字段
        assertEquals(1, user.getId());
        assertEquals("testaccount", user.getUserAccount());
        assertEquals("测试用户", user.getUserName());
        assertEquals("password123", user.getUserPwd());
        assertEquals("avatar.jpg", user.getUserAvatar());
        assertEquals("test@example.com", user.getUserEmail());
        assertEquals(0, user.getUserRole());
        assertEquals(true, user.getIsLogin());
        assertEquals(false, user.getIsWord());
        assertEquals(now, user.getCreateTime());
    }
    
    @Test
    void testAllArgsConstructor() {
        // 测试全参数构造函数
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1, "testaccount", "测试用户", "password123", "avatar.jpg", 
                "test@example.com", 0, true, false, now);
        
        // 验证所有字段
        assertEquals(1, user.getId());
        assertEquals("testaccount", user.getUserAccount());
        assertEquals("测试用户", user.getUserName());
        assertEquals("password123", user.getUserPwd());
        assertEquals("avatar.jpg", user.getUserAvatar());
        assertEquals("test@example.com", user.getUserEmail());
        assertEquals(0, user.getUserRole());
        assertEquals(true, user.getIsLogin());
        assertEquals(false, user.getIsWord());
        assertEquals(now, user.getCreateTime());
    }
    
    @Test
    void testEqualsAndHashCode() {
        // 测试equals和hashCode方法
        User user1 = User.builder().id(1).userAccount("test").build();
        User user2 = User.builder().id(1).userAccount("test").build();
        User user3 = User.builder().id(2).userAccount("test2").build();
        
        // 相同对象应该相等
        assertEquals(user1, user1);
        
        // 具有相同属性的不同对象应该相等
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        
        // 具有不同属性的对象不应该相等
        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
    
    @Test
    void testToString() {
        // 测试toString方法
        User user = new User();
        
        try {
            // 使用反射设置字段
            Field idField = User.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(user, 1);
            
            Field userAccountField = User.class.getDeclaredField("userAccount");
            userAccountField.setAccessible(true);
            userAccountField.set(user, "testaccount");
            
            Field userNameField = User.class.getDeclaredField("userName");
            userNameField.setAccessible(true);
            userNameField.set(user, "测试用户");
            
            // 测试toString方法
            String toString = user.toString();
            
            // toString应该包含所有属性信息
            assertNotNull(toString);
            
            // 验证是否存在基本的字段信息，不验证完整格式
            assertTrue(toString.contains("id") && toString.contains("1"));
            assertTrue(toString.contains("userAccount") && toString.contains("testaccount"));
            assertTrue(toString.contains("userName") && toString.contains("测试用户"));
        } catch (Exception e) {
            fail("反射操作失败: " + e.getMessage());
        }
    }
} 