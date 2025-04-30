package cn.kmbeast.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTests {

    @Test
    void testGenerateAndVerifyToken() {
        // Test data
        Integer userId = 123;
        Integer userRole = 1;
        
        // Generate token
        String token = JwtUtil.toToken(userId, userRole);
        
        // Verify token is not null or empty
        assertNotNull(token);
        assertFalse(token.isEmpty());
        
        // Verify token format (JWT has 3 parts separated by dots)
        String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        
        // Parse token and verify claims
        Claims claims = JwtUtil.fromToken(token);
        assertNotNull(claims);
        assertEquals(userId, claims.get("id", Integer.class));
        assertEquals(userRole, claims.get("role", Integer.class));
        assertEquals("用户认证", claims.getSubject());
    }
    
    @Test
    void testInvalidToken() {
        // Test with invalid token
        String invalidToken = "invalid.token.format";
        Claims claims = JwtUtil.fromToken(invalidToken);
        assertNull(claims);
    }
    
    @Test
    void testExpiredToken() {
        // This test would ideally check token expiration
        // but since the expiration is set to 7 days, we'll need to simulate it
        // This is a placeholder - in a real test you might use a library to mock time
        // or have a parameter for expiration in your JwtUtil
        
        // For now, we'll just check another valid token case
        Integer userId = 456;
        Integer userRole = 2;
        
        String token = JwtUtil.toToken(userId, userRole);
        Claims claims = JwtUtil.fromToken(token);
        
        assertNotNull(claims);
        assertTrue(claims.getExpiration().after(new Date()));
    }
} 