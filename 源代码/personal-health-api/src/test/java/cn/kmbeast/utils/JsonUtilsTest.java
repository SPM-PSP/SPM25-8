package cn.kmbeast.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * JsonUtils工具类测试
 */
class JsonUtilsTest {

    @Test
    void testConvertObj2Json() {
        // 测试Map转JSON
        Map<String, Object> map = new HashMap<>();
        map.put("name", "测试用户");
        map.put("age", 25);
        map.put("active", true);
        
        String json = JsonUtils.convertObj2Json(map);
        
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"测试用户\""));
        assertTrue(json.contains("\"age\":25"));
        assertTrue(json.contains("\"active\":true"));
    }
    
    @Test
    void testConvertJson2Obj() {
        // 创建测试JSON
        String json = "{\"name\":\"测试用户\",\"age\":25,\"active\":true}";
        
        // 转换为Map对象
        Map map = JsonUtils.convertJson2Obj(json, Map.class);
        
        assertNotNull(map);
        assertEquals("测试用户", map.get("name"));
        assertEquals(25, ((Number) map.get("age")).intValue());
        assertEquals(true, map.get("active"));
    }
    
    @Test
    void testConvertJsonArray2List() {
        // 创建测试JSON数组
        String jsonArray = "[{\"name\":\"用户1\",\"age\":25},{\"name\":\"用户2\",\"age\":30}]";
        
        // 转换为List<Map>
        List<Map> list = JsonUtils.convertJsonArray2List(jsonArray, Map.class);
        
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals("用户1", list.get(0).get("name"));
        assertEquals(25, ((Number) list.get(0).get("age")).intValue());
        assertEquals("用户2", list.get(1).get("name"));
        assertEquals(30, ((Number) list.get(1).get("age")).intValue());
    }
} 