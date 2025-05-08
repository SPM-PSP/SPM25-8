package cn.kmbeast.context;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalThreadHolderTest {

    @AfterEach
    void tearDown() {
        // 每个测试执行后清除ThreadLocal防止污染
        LocalThreadHolder.clear();
    }

    @Test
    void testSetAndGetUserIdAndRoleId() {
        Integer testUserId = 42;
        Integer testUserRole = 1;

        LocalThreadHolder.setUserId(testUserId, testUserRole);

        assertEquals(testUserId, LocalThreadHolder.getUserId(), "用户ID获取错误");
        assertEquals(testUserRole, LocalThreadHolder.getRoleId(), "用户角色获取错误");
    }

    @Test
    void testClearRemovesData() {
        LocalThreadHolder.setUserId(100, 2);

        // 确保设置成功
        assertNotNull(LocalThreadHolder.getUserId());
        assertNotNull(LocalThreadHolder.getRoleId());

        // 清除后应为 null 或抛出 NPE（取决于是否调用了 get() 后再 get("key")）
        LocalThreadHolder.clear();

        assertThrows(NullPointerException.class, LocalThreadHolder::getUserId);
        assertThrows(NullPointerException.class, LocalThreadHolder::getRoleId);
    }

    @Test
    void testThreadIsolation() throws InterruptedException {
        LocalThreadHolder.setUserId(1, 1);

        Thread thread = new Thread(() -> {
            // 子线程应为独立上下文
            assertThrows(NullPointerException.class, LocalThreadHolder::getUserId);
            assertThrows(NullPointerException.class, LocalThreadHolder::getRoleId);

            // 设置子线程自己的值
            LocalThreadHolder.setUserId(2, 3);
            assertEquals(2, LocalThreadHolder.getUserId());
            assertEquals(3, LocalThreadHolder.getRoleId());

            // 清除子线程的值
            LocalThreadHolder.clear();
        });

        thread.start();
        thread.join();

        // 主线程的值应不受影响
        assertEquals(1, LocalThreadHolder.getUserId());
        assertEquals(1, LocalThreadHolder.getRoleId());
    }
}
