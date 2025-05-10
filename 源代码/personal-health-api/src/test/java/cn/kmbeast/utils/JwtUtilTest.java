package cn.kmbeast.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    @Test
    void testToTokenAndFromToken_Valid() {
        // 生成 token
        Integer userId = 101;
        Integer userRole = 2;
        String token = JwtUtil.toToken(userId, userRole);

        // 解析 token
        Claims claims = JwtUtil.fromToken(token);

        assertNotNull(claims, "Claims should not be null for a valid token");
        assertEquals(userId, claims.get("id", Integer.class));
        assertEquals(userRole, claims.get("role", Integer.class));
        assertEquals("用户认证", claims.getSubject());
    }

    @Test
    void testFromToken_InvalidTokenFormat() {
        // 模拟非法 token
        String invalidToken = "not.a.valid.token";
        Claims claims = JwtUtil.fromToken(invalidToken);

        assertNull(claims, "Claims should be null for an invalid token");
    }

    @Test
    void testFromToken_EmptyToken() {
        String emptyToken = "";
        Claims claims = JwtUtil.fromToken(emptyToken);

        assertNull(claims, "Claims should be null for an empty token");
    }

    @Test
    void testFromToken_NullToken() {
        String nullToken = null;
        Claims claims = JwtUtil.fromToken(nullToken);

        assertNull(claims, "Claims should be null for a null token");
    }

    // 可选测试：构造一个非法签名 token（用不同 secret 生成），模拟伪造 token 情况
    @Test
    void testFromToken_TamperedToken() {
        // 伪造 token：修改真实 token 内容
        String token = JwtUtil.toToken(101, 1);
        // 随便篡改一位
        String tamperedToken = token.substring(0, token.length() - 2) + "zz";

        Claims claims = JwtUtil.fromToken(tamperedToken);
        assertNull(claims, "Claims should be null for a tampered token");
    }
}
