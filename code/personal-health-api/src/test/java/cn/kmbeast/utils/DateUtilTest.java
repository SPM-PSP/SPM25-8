package cn.kmbeast.utils;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import cn.kmbeast.pojo.vo.ChartVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DateUtil工具类测试
 */
class DateUtilTest {

    @Test
    void testStartAndEndTimeWithAllData() {
        // 测试获取所有数据的情况 (-1)
        QueryDto queryDto = DateUtil.startAndEndTime(-1);
        
        // 此情况下应该返回空的查询DTO
        assertNotNull(queryDto);
        // 使用反射访问startTime和endTime字段
        try {
            java.lang.reflect.Field startTimeField = QueryDto.class.getDeclaredField("startTime");
            startTimeField.setAccessible(true);
            assertNull(startTimeField.get(queryDto));
            
            java.lang.reflect.Field endTimeField = QueryDto.class.getDeclaredField("endTime");
            endTimeField.setAccessible(true);
            assertNull(endTimeField.get(queryDto));
        } catch (Exception e) {
            fail("反射访问字段失败: " + e.getMessage());
        }
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 7, 30})
    void testStartAndEndTimeWithDays(int days) {
        // 测试不同天数范围
        QueryDto queryDto = DateUtil.startAndEndTime(days);
        
        // 验证结果
        assertNotNull(queryDto);
        
        try {
            // 通过反射访问私有字段
            java.lang.reflect.Field startTimeField = QueryDto.class.getDeclaredField("startTime");
            startTimeField.setAccessible(true);
            LocalDateTime startTime = (LocalDateTime) startTimeField.get(queryDto);
            assertNotNull(startTime);
            
            java.lang.reflect.Field endTimeField = QueryDto.class.getDeclaredField("endTime");
            endTimeField.setAccessible(true);
            LocalDateTime endTime = (LocalDateTime) endTimeField.get(queryDto);
            assertNotNull(endTime);
            
            // 验证开始时间在结束时间之前
            assertTrue(startTime.isBefore(endTime));
            
            // 验证结束时间接近当前时间
            LocalDateTime now = LocalDateTime.now();
            // 允许1秒的误差
            assertTrue(Math.abs(endTime.toLocalTime().toSecondOfDay() - now.toLocalTime().toSecondOfDay()) < 2);
        } catch (Exception e) {
            fail("反射访问字段失败: " + e.getMessage());
        }
    }
    
    @Test
    void testCountDatesWithinRangeEmpty() {
        // 测试空数据的情况
        List<LocalDateTime> emptyDates = new ArrayList<>();
        List<ChartVO> result = DateUtil.countDatesWithinRange(7, emptyDates);
        
        // 应该返回空列表
        assertNotNull(result);
        assertEquals(0, result.size());
    }
    
    @Test
    void testCountDatesWithinRange() {
        // 创建测试数据
        List<LocalDateTime> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        
        // 添加当天的3条记录
        dates.add(LocalDateTime.of(today, LocalTime.of(9, 0)));
        dates.add(LocalDateTime.of(today, LocalTime.of(13, 0)));
        dates.add(LocalDateTime.of(today, LocalTime.of(18, 0)));
        
        // 添加昨天的2条记录
        LocalDate yesterday = today.minusDays(1);
        dates.add(LocalDateTime.of(yesterday, LocalTime.of(10, 0)));
        dates.add(LocalDateTime.of(yesterday, LocalTime.of(14, 0)));
        
        // 统计7天内的记录
        List<ChartVO> result = DateUtil.countDatesWithinRange(7, dates);
        
        // 验证结果
        assertNotNull(result);
        assertEquals(2, result.size()); // 应该只有2天有数据
        
        // 验证日期格式和计数
        for (ChartVO vo : result) {
            try {
                // 通过反射访问ChartVO的字段
                java.lang.reflect.Field nameField = ChartVO.class.getDeclaredField("name");
                nameField.setAccessible(true);
                String name = (String) nameField.get(vo);
                
                java.lang.reflect.Field countField = ChartVO.class.getDeclaredField("count");
                countField.setAccessible(true);
                Integer count = (Integer) countField.get(vo);
                
                if (name.equals(String.format("%02d-%02d", today.getMonthValue(), today.getDayOfMonth()))) {
                    assertEquals(3, count.intValue());
                } else if (name.equals(String.format("%02d-%02d", yesterday.getMonthValue(), yesterday.getDayOfMonth()))) {
                    assertEquals(2, count.intValue());
                }
            } catch (Exception e) {
                fail("反射访问字段失败: " + e.getMessage());
            }
        }
    }
} 