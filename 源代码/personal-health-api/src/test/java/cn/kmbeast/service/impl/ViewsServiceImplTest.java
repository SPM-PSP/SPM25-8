package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.HealthModelConfigMapper;
import cn.kmbeast.mapper.NewsMapper;
import cn.kmbeast.mapper.UserHealthMapper;
import cn.kmbeast.mapper.UserMapper;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.vo.ChartVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewsServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private NewsMapper newsMapper;

    @Mock
    private HealthModelConfigMapper healthModelConfigMapper;

    @Mock
    private UserHealthMapper userHealthMapper;

    @InjectMocks
    private ViewsServiceImpl viewsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testStaticControls_ShouldReturnCorrectResult() throws Exception {
        // Arrange
        when(userMapper.queryCount(any())).thenReturn(10);
        when(newsMapper.queryCount(any())).thenReturn(5);
        when(healthModelConfigMapper.queryCount(any())).thenReturn(3);
        when(userHealthMapper.queryCount(any())).thenReturn(20);

        // Act
        Result<List<ChartVO>> result = viewsService.staticControls();

        // Assert basic status
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMsg());

        // 反射获取私有 data 字段（因为你没有定义 getData()）
        Field dataField = result.getClass().getDeclaredField("data");
        dataField.setAccessible(true);
        Object data = dataField.get(result);

        assertNotNull(data);
        assertTrue(data instanceof List<?>);
        List<?> list = (List<?>) data;

        assertEquals(4, list.size());

        // 验证每一项类型和值
        for (Object item : list) {
            assertTrue(item instanceof ChartVO);
        }

        verify(userMapper).queryCount(any());
        verify(newsMapper).queryCount(any());
        verify(healthModelConfigMapper).queryCount(any());
        verify(userHealthMapper).queryCount(any());
    }
}
