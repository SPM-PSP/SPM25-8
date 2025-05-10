package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.EvaluationsMapper;
import cn.kmbeast.mapper.UserMapper;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.EvaluationsQueryDto;
import cn.kmbeast.pojo.entity.Evaluations;
import cn.kmbeast.pojo.entity.User;
import cn.kmbeast.pojo.vo.CommentChildVO;
import cn.kmbeast.pojo.vo.CommentParentVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EvaluationsServiceTest {

    @InjectMocks
    private EvaluationsServiceImpl evaluationsService;

    @Mock
    private EvaluationsMapper evaluationsMapper;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInsert_Success() {
        LocalThreadHolder.setUserId(1, 1);
        Evaluations evaluations = new Evaluations();
        evaluations.setContent("Valid Comment");

        User user = User.builder().id(1).isWord(false).build();
        when(userMapper.getByActive(any(User.class))).thenReturn(user);

        Result<Object> result = evaluationsService.insert(evaluations);

        assertEquals(200, result.getCode());
        verify(evaluationsMapper).save(any(Evaluations.class));
    }

    @Test
    void testInsert_UserMuted() {
        LocalThreadHolder.setUserId(1, 1);
        Evaluations evaluations = new Evaluations();

        User user = User.builder().id(1).isWord(true).build();
        when(userMapper.getByActive(any(User.class))).thenReturn(user);

        Result<Object> result = evaluationsService.insert(evaluations);

        assertEquals(400, result.getCode());
        assertEquals("账户已被禁言", result.getMsg());
        verify(evaluationsMapper, never()).save(any());
    }

    @Test
    void testInsert_UserNotFound() {
        LocalThreadHolder.setUserId(1, 1);
        Evaluations evaluations = new Evaluations();
        when(userMapper.getByActive(any(User.class))).thenReturn(null);

        Result<Object> result = evaluationsService.insert(evaluations);

        assertEquals(400, result.getCode());
        assertEquals("账户不存在", result.getMsg());
    }

    @Test
    void testInsert_NullContent() {
        LocalThreadHolder.setUserId(1, 1);
        Evaluations evaluations = new Evaluations();  // content 为 null

        User user = User.builder().id(1).isWord(false).build();
        when(userMapper.getByActive(any(User.class))).thenReturn(user);

        Result<Object> result = evaluationsService.insert(evaluations);

        assertEquals(200, result.getCode()); // 允许空内容插入
        verify(evaluationsMapper).save(any());
    }

    @Test
    void testList_WithResults() {
        int contentId = 1;
        String contentType = "news";
        LocalThreadHolder.setUserId(1, 1);

        CommentParentVO parent = new CommentParentVO();
        parent.setUpvoteList("1,2");

        CommentChildVO child = new CommentChildVO();
        child.setUpvoteList("2,3");
        parent.setCommentChildVOS(Collections.singletonList(child));

        when(evaluationsMapper.totalCount(contentId, contentType)).thenReturn(1);

        Result<Object> result = evaluationsService.list(contentId, contentType);

        assertEquals(200, result.getCode());
    }

    @Test
    void testList_Empty() {
        int contentId = 1;
        String contentType = "news";
        LocalThreadHolder.setUserId(1, 1);

        when(evaluationsMapper.getParentComments(contentId, contentType)).thenReturn(Collections.emptyList());
        when(evaluationsMapper.totalCount(contentId, contentType)).thenReturn(0);

        Result<Object> result = evaluationsService.list(contentId, contentType);

        assertEquals(200, result.getCode());
    }

    @Test
    void testQuery_Success() {
        EvaluationsQueryDto queryDto = new EvaluationsQueryDto();
        List<CommentChildVO> list = Collections.singletonList(new CommentChildVO());

        when(evaluationsMapper.query(queryDto)).thenReturn(list);
        when(evaluationsMapper.queryCount(queryDto)).thenReturn(1);

        Result<Object> result = evaluationsService.query(queryDto);

        assertEquals(200, result.getCode());
    }

    @Test
    void testQuery_Empty() {
        EvaluationsQueryDto queryDto = new EvaluationsQueryDto();

        when(evaluationsMapper.query(queryDto)).thenReturn(Collections.emptyList());
        when(evaluationsMapper.queryCount(queryDto)).thenReturn(0);

        Result<Object> result = evaluationsService.query(queryDto);

        assertEquals(200, result.getCode());
    }

    @Test
    void testBatchDelete_Success() {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        Result<Object> result = evaluationsService.batchDelete(ids);

        assertEquals(200, result.getCode());
        verify(evaluationsMapper).batchDelete(ids);
    }

    @Test
    void testBatchDelete_EmptyList() {
        List<Integer> ids = new ArrayList<>();
        Result<Object> result = evaluationsService.batchDelete(ids);

        assertEquals(200, result.getCode()); // 根据实现仍返回成功
        verify(evaluationsMapper).batchDelete(ids);
    }

    @Test
    void testDelete_Success() {
        Integer id = 5;
        Result<String> result = evaluationsService.delete(id);

        assertEquals(200, result.getCode());
        verify(evaluationsMapper).batchDelete(Collections.singletonList(id));
    }

    @Test
    void testUpdate_Success() {
        Evaluations evaluation = new Evaluations();
        evaluation.setId(1);
        evaluation.setContent("Updated");

        Result<Void> result = evaluationsService.update(evaluation);

        assertEquals(200, result.getCode());
        verify(evaluationsMapper).update(evaluation);
    }
}
