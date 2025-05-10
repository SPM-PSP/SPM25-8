package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.HealthModelConfigMapper;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HealthModelConfigQueryDto;
import cn.kmbeast.pojo.entity.HealthModelConfig;
import cn.kmbeast.pojo.vo.HealthModelConfigVO;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealthModelConfigServiceImplTest {

    @InjectMocks
    private HealthModelConfigServiceImpl service;

    @Mock
    private HealthModelConfigMapper healthModelConfigMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        LocalThreadHolder.setUserId(1, 0); // 设置线程用户ID和角色
    }

    @AfterEach
    void tearDown() {
        LocalThreadHolder.clear(); // 清理线程变量
    }

    @Test
    void testSave() {
        HealthModelConfig config = new HealthModelConfig();
        Result<Void> result = service.save(config);

        assertEquals(200, result.getCode());
        assertEquals(1, config.getUserId());
        verify(healthModelConfigMapper, times(1)).save(config);
    }

    @Test
    void testBatchDelete() {
        List<Long> ids = Arrays.asList(1L, 2L);
        Result<Void> result = service.batchDelete(ids);

        assertEquals(200, result.getCode());
        verify(healthModelConfigMapper, times(1)).batchDelete(ids);
    }

    @Test
    void testUpdate() {
        HealthModelConfig config = new HealthModelConfig();
        Result<Void> result = service.update(config);

        assertEquals(200, result.getCode());
        verify(healthModelConfigMapper, times(1)).update(config);
    }

    @Test
    void testModelList() {
        // 模拟返回数据
        HealthModelConfigVO userModel = new HealthModelConfigVO();

        when(healthModelConfigMapper.query(argThat(dto -> dto.getUserId() != null)))
                .thenReturn(Collections.singletonList(userModel));

        Result<List<HealthModelConfigVO>> result = service.modelList();

        assertEquals(200, result.getCode());
    }

    @Test
    void testQuery() {
        HealthModelConfigQueryDto dto = new HealthModelConfigQueryDto();
        List<HealthModelConfigVO> mockList = Arrays.asList(new HealthModelConfigVO());

        when(healthModelConfigMapper.query(dto)).thenReturn(mockList);
        when(healthModelConfigMapper.queryCount(dto)).thenReturn(1);

        Result<List<HealthModelConfigVO>> result = service.query(dto);

        assertEquals(200, result.getCode());
    }
}
