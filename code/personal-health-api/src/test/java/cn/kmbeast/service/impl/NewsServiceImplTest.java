package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.NewsMapper;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.NewsQueryDto;
import cn.kmbeast.pojo.entity.News;
import cn.kmbeast.pojo.vo.NewsVO;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewsServiceImplTest {

    @InjectMocks
    private NewsServiceImpl newsService;

    @Mock
    private NewsMapper newsMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        LocalThreadHolder.setUserId(1, 0); // 设置线程用户信息
    }


    @AfterEach
    void tearDown() {
        LocalThreadHolder.clear(); // 清理线程变量
    }

    @Test
    void testSave() {
        News news = new News();
        Result<Void> result = newsService.save(news);

        assertEquals(200, result.getCode());
        assertNotNull(news.getCreateTime());
        verify(newsMapper, times(1)).save(news);
    }

    @Test
    void testBatchDelete() {
        List<Long> ids = Arrays.asList(101L, 102L);
        Result<Void> result = newsService.batchDelete(ids);

        assertEquals(200, result.getCode());
        verify(newsMapper, times(1)).batchDelete(ids);
    }

    @Test
    void testUpdate() {
        News news = new News();
        Result<Void> result = newsService.update(news);

        assertEquals(200, result.getCode());
        verify(newsMapper, times(1)).update(news);
    }

    @Test
    void testQuery() {
        NewsQueryDto dto = new NewsQueryDto();
        List<NewsVO> mockList = Arrays.asList(new NewsVO(), new NewsVO());

        when(newsMapper.query(dto)).thenReturn(mockList);
        when(newsMapper.queryCount(dto)).thenReturn(2);

        Result<List<NewsVO>> result = newsService.query(dto);

        assertEquals(200, result.getCode());
        assertEquals(4, result.getMsg().length());
        verify(newsMapper, times(1)).query(dto);
        verify(newsMapper, times(1)).queryCount(dto);
    }

}
