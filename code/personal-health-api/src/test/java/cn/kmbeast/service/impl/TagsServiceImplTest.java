package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.TagsMapper;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.TagsQueryDto;
import cn.kmbeast.pojo.entity.Tags;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TagsServiceImplTest {

    @InjectMocks
    private TagsServiceImpl tagsService;

    @Mock
    private TagsMapper tagsMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        Tags tag = new Tags();
        Result<Void> result = tagsService.save(tag);

        assertEquals(200, result.getCode());
        verify(tagsMapper, times(1)).save(tag);
    }

    @Test
    void testBatchDelete() {
        List<Long> ids = Arrays.asList(10L, 20L);
        Result<Void> result = tagsService.batchDelete(ids);

        assertEquals(200, result.getCode());
        verify(tagsMapper, times(1)).batchDelete(ids);
    }

    @Test
    void testUpdate() {
        Tags tag = new Tags();
        Result<Void> result = tagsService.update(tag);

        assertEquals(200, result.getCode());
        verify(tagsMapper, times(1)).update(tag);
    }

    @Test
    void testQuery() {
        TagsQueryDto dto = new TagsQueryDto();
        List<Tags> mockList = Arrays.asList(new Tags(), new Tags());

        when(tagsMapper.query(dto)).thenReturn(mockList);
        when(tagsMapper.queryCount(dto)).thenReturn(2);

        Result<List<Tags>> result = tagsService.query(dto);

        assertEquals(200, result.getCode());
        assertEquals(4, result.getMsg().length());
        verify(tagsMapper, times(1)).query(dto);
        verify(tagsMapper, times(1)).queryCount(dto);
    }
}
