package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.NewsSaveMapper;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NewsSaveQueryDto;
import cn.kmbeast.pojo.entity.NewsSave;
import cn.kmbeast.pojo.vo.NewsSaveVO;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewsSaveServiceImplTest {

    @InjectMocks
    private NewsSaveServiceImpl newsSaveService;

    @Mock
    private NewsSaveMapper newsSaveMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        LocalThreadHolder.setUserId(1, 0); // 统一设置用户ID和角色
    }

    @AfterEach
    void tearDown() {
        LocalThreadHolder.clear(); // 清理线程变量
    }

    @Test
    void testSave() {
        NewsSave newsSave = new NewsSave();
        Result<Void> result = newsSaveService.save(newsSave);

        assertEquals(200, result.getCode());
        assertNotNull(newsSave.getCreateTime());
        assertNull(newsSave.getUserId());
        verify(newsSaveMapper, times(1)).save(newsSave);
    }

    @Test
    void testBatchDelete() {
        List<Long> ids = Arrays.asList(1L, 2L);
        Result<Void> result = newsSaveService.batchDelete(ids);

        assertEquals(200, result.getCode());
        verify(newsSaveMapper, times(1)).batchDelete(ids);
    }

    @Test
    void testQuery() {
        NewsSaveQueryDto dto = new NewsSaveQueryDto();
        List<NewsSaveVO> mockList = Collections.singletonList(new NewsSaveVO());

        when(newsSaveMapper.query(dto)).thenReturn(mockList);
        when(newsSaveMapper.queryCount(dto)).thenReturn(1);

        Result<List<NewsSaveVO>> result = newsSaveService.query(dto);

        assertEquals(200, result.getCode());
        assertEquals(4, result.getMsg().length());
        verify(newsSaveMapper, times(1)).query(dto);
        verify(newsSaveMapper, times(1)).queryCount(dto);
    }

    @Test
    void testOperation_SaveNew() {
        NewsSave newsSave = new NewsSave();
        newsSave.setNewsId(99);

        when(newsSaveMapper.query(any())).thenReturn(Collections.emptyList());

        Result<Void> result = newsSaveService.operation(newsSave);

        assertEquals(200, result.getCode());
        assertEquals(1, newsSave.getUserId());
        assertNotNull(newsSave.getCreateTime());
        verify(newsSaveMapper, times(1)).save(newsSave);
    }

    @Test
    void testOperation_DeleteExisting() {
        NewsSave newsSave = new NewsSave();
        newsSave.setNewsId(88);

        Result<Void> result = newsSaveService.operation(newsSave);

        assertEquals(200, result.getCode());
    }
}
