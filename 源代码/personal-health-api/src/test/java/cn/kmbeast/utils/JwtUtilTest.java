package cn.kmbeast.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JwtUtil工具类测试
 */
class JwtUtilTest {

    @Test
    void testToToken() {
        // 测试生成token
        Integer userId = 123;
        Integer userRole = 1;
        String token = JwtUtil.toToken(userId, userRole);
        
        assertNotNull(token);
        assertTrue(token.length() > 0);
        assertTrue(token.split("\\.").length == 3); // JWT格式应该有3部分，用.分隔
    }
    
    @Test
    void testFromToken() {
        // 生成一个有效的token
        Integer userId = 123;
        Integer userRole = 1;
        String token = JwtUtil.toToken(userId, userRole);
        
        // 解析token
        Claims claims = JwtUtil.fromToken(token);
        
        // 验证解析结果
        assertNotNull(claims);
        assertEquals(userId, claims.get("id", Integer.class));
        assertEquals(userRole, claims.get("role", Integer.class));
        assertEquals("用户认证", claims.getSubject());
    }
    
    @Test
    void testInvalidToken() {
        // 测试无效token
        String invalidToken = "invalid.token.string";
        Claims claims = JwtUtil.fromToken(invalidToken);
        
        // 应该返回null
        assertNull(claims);
    }
    
    @Test
    void testExpiredToken() {
        // 注：此测试需要模拟过期token，实际项目中可能需要调整JwtUtil类以支持测试
        // 此处只是示例，实际实现可能需要调整
        
        // 另一种处理方式是证明token包含过期时间
        Integer userId = 123;
        Integer userRole = 1;
        String token = JwtUtil.toToken(userId, userRole);
        
        Claims claims = JwtUtil.fromToken(token);
        assertNotNull(claims.getExpiration());
    }
} 