package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.UserQueryDto;
import cn.kmbeast.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsertAndGetByActive() {
        // Create a user
        User user = User.builder()
                .userName("testuser")
                .userAccount("testaccount")
                .userPwd("password")
                .userEmail("test@example.com")
                .createTime(LocalDateTime.now())
                .isLogin(false)
                .isWord(true)
                .build();
        
        // Insert the user
        int rows = userMapper.insert(user);
        assertEquals(1, rows, "Insert should affect 1 row");
        
        // Verify the user was inserted by getting it
        User retrieved = userMapper.getByActive(User.builder().userAccount("testaccount").build());
        assertNotNull(retrieved, "User should be retrieved");
        assertEquals("testuser", retrieved.getUserName(), "Username should match");
        assertEquals("testaccount", retrieved.getUserAccount(), "Account should match");
    }
    
    @Test
    void testUpdate() {
        // Create a user
        User user = User.builder()
                .userName("updateuser")
                .userAccount("updateaccount")
                .userPwd("password")
                .userEmail("update@example.com")
                .createTime(LocalDateTime.now())
                .isLogin(false)
                .isWord(true)
                .build();
        
        // Insert the user
        userMapper.insert(user);
        
        // Retrieve to get the ID
        User inserted = userMapper.getByActive(User.builder().userAccount("updateaccount").build());
        
        // Update the user
        inserted.setUserName("updatedname");
        int rows = userMapper.update(inserted);
        assertEquals(1, rows, "Update should affect 1 row");
        
        // Verify the update
        User updated = userMapper.getByActive(User.builder().id(inserted.getId()).build());
        assertEquals("updatedname", updated.getUserName(), "Username should be updated");
    }
    
    @Test
    void testQuery() {
        // Insert multiple users for testing
        for (int i = 0; i < 3; i++) {
            User user = User.builder()
                    .userName("queryuser" + i)
                    .userAccount("queryaccount" + i)
                    .userPwd("password")
                    .createTime(LocalDateTime.now())
                    .isLogin(false)
                    .isWord(true)
                    .build();
            userMapper.insert(user);
        }
        
        // Create query parameters
        UserQueryDto queryDto = new UserQueryDto();
        // Set necessary query parameters
        
        // Query users
        List<User> users = userMapper.query(queryDto);
        assertNotNull(users, "Query result should not be null");
        
        // Count the results
        int count = userMapper.queryCount(queryDto);
        assertTrue(count >= 3, "Count should include at least the 3 users we added");
    }
    
    @Test
    void testBatchDelete() {
        // Insert users for testing
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = User.builder()
                    .userName("deleteuser" + i)
                    .userAccount("deleteaccount" + i)
                    .userPwd("password")
                    .createTime(LocalDateTime.now())
                    .isLogin(false)
                    .isWord(true)
                    .build();
            userMapper.insert(user);
            
            // Get the ID of the inserted user
            User inserted = userMapper.getByActive(User.builder().userAccount("deleteaccount" + i).build());
            ids.add(inserted.getId());
        }
        
        // Delete the users
        userMapper.batchDelete(ids);
        
        // Verify they were deleted
        for (Integer id : ids) {
            User deleted = userMapper.getByActive(User.builder().id(id).build());
            assertNull(deleted, "User with ID " + id + " should be deleted");
        }
    }
} 