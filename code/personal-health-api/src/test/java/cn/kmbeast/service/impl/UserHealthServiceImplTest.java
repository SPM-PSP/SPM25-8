package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.HealthModelConfigMapper;
import cn.kmbeast.mapper.UserHealthMapper;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.HealthModelConfigQueryDto;
import cn.kmbeast.pojo.dto.query.extend.UserHealthQueryDto;
import cn.kmbeast.pojo.entity.HealthModelConfig;
import cn.kmbeast.pojo.entity.UserHealth;
import cn.kmbeast.pojo.vo.HealthModelConfigVO;
import cn.kmbeast.pojo.vo.UserHealthVO;
import cn.kmbeast.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserHealthServiceImplTest {

    @InjectMocks
    private UserHealthServiceImpl userHealthService;

    @Mock
    private UserHealthMapper userHealthMapper;

    @Mock
    private HealthModelConfigMapper healthModelConfigMapper;

    @Mock
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        LocalThreadHolder.setUserId(1, 1); // mock 当前线程用户
    }

    @Test
    void testSave_NormalData_NoMessageTriggered() {
        UserHealth userHealth = new UserHealth();
        userHealth.setHealthModelConfigId(100);
        userHealth.setValue("150");

        HealthModelConfigVO configVO = new HealthModelConfigVO();
        configVO.setName("血压");
        configVO.setValueRange("100,200");

        when(healthModelConfigMapper.query(any(HealthModelConfigQueryDto.class)))
                .thenReturn(Arrays.asList(configVO));
        List<UserHealth> input = new ArrayList<>();
        input.add(userHealth);

        Result<Void> result = userHealthService.save(input);

        verify(userHealthMapper).batchSave(anyList());
        verify(messageService, never()).dataWordSave(any());
        assertEquals(200, result.getCode());
    }

    @Test
    void testSave_AbnormalValue_MessageTriggered() {
        UserHealth userHealth = new UserHealth();
        userHealth.setHealthModelConfigId(101);
        userHealth.setValue("300");

        HealthModelConfig config = new HealthModelConfig();
        config.setName("血压");
        config.setValueRange("100,200");

        HealthModelConfigVO configVO = new HealthModelConfigVO();
        configVO.setName("血压");
        configVO.setValueRange("100,200");

        when(healthModelConfigMapper.query(any(HealthModelConfigQueryDto.class)))
                .thenReturn(Arrays.asList(configVO));

        List<UserHealth> input = Arrays.asList(userHealth);

        Result<Void> result = userHealthService.save(input);

        verify(messageService).dataWordSave(any());
        verify(userHealthMapper).batchSave(anyList());
        assertEquals(200, result.getCode());
    }

    @Test
    void testBatchDelete() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        Result<Void> result = userHealthService.batchDelete(ids);
        verify(userHealthMapper).batchDelete(ids);
        assertEquals(200, result.getCode());
    }

    @Test
    void testUpdate() {
        UserHealth userHealth = new UserHealth();
        Result<Void> result = userHealthService.update(userHealth);
        verify(userHealthMapper).update(userHealth);
        assertEquals(200, result.getCode());
    }

    @Test
    void testQuery() {
        UserHealthQueryDto queryDto = new UserHealthQueryDto();
        List<UserHealthVO> voList = Arrays.asList(new UserHealthVO());

        when(userHealthMapper.query(queryDto)).thenReturn(voList);
        when(userHealthMapper.queryCount(queryDto)).thenReturn(1);
    }

    @Test
    void testDaysQuery() {
        UserHealthVO vo = new UserHealthVO();
        vo.setCreateTime(LocalDateTime.now());

        when(userHealthMapper.query(any(UserHealthQueryDto.class)))
                .thenReturn(Arrays.asList(vo));

        Result<?> result = userHealthService.daysQuery(7);
        assertEquals(200, result.getCode());
        assertNotNull(result.getMsg());
    }
    @Test
    void testSave_HealthConfigNotFound_ShouldNotTriggerMessage() {
        UserHealth userHealth = new UserHealth();
        userHealth.setHealthModelConfigId(102);
        userHealth.setValue("150");

        when(healthModelConfigMapper.query(any())).thenReturn(Collections.emptyList());

        userHealthService.save(Arrays.asList(userHealth));

        verify(messageService, never()).dataWordSave(any());
        verify(userHealthMapper).batchSave(anyList());
    }

}
