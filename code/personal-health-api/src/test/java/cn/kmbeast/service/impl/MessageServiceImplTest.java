package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.MessageMapper;
import cn.kmbeast.mapper.UserMapper;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.MessageQueryDto;
import cn.kmbeast.pojo.dto.query.extend.UserQueryDto;
import cn.kmbeast.pojo.em.IsReadEnum;
import cn.kmbeast.pojo.em.RoleEnum;
import cn.kmbeast.pojo.entity.Message;
import cn.kmbeast.pojo.entity.User;
import cn.kmbeast.pojo.vo.MessageVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageServiceImplTest {

    @Mock
    private MessageMapper messageMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private MessageServiceImpl messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        Message msg = new Message();
        List<Message> list = Collections.singletonList(msg);
        Result<Void> result = messageService.save(list);
        assertEquals(200, result.getCode());
        verify(messageMapper).batchSave(list);
    }

    @Test
    void testEvaluationsReplySave() {
        Message msg = new Message();
        Result<Void> result = messageService.evaluationsReplySave(msg);
        assertEquals(200, result.getCode());
        assertEquals("你的评论被回复了", msg.getContent());
        verify(messageMapper).batchSave(any());
    }

    @Test
    void testEvaluationsUpvoteSave() {
        Message msg = new Message();
        Result<Void> result = messageService.evaluationsUpvoteSave(msg);
        assertEquals(200, result.getCode());
        assertEquals("你的评论被点赞了", msg.getContent());
        verify(messageMapper).batchSave(any());
    }

    @Test
    void testSystemInfoSave() {
        List<Message> list = Arrays.asList(new Message(), new Message());
        Result<Void> result = messageService.systemInfoSave(list);
        assertEquals(200, result.getCode());
        verify(messageMapper).batchSave(list);
    }

    @Test
    void testDataWordSave() {
        List<Message> list = Arrays.asList(new Message(), new Message());
        Result<Void> result = messageService.dataWordSave(list);
        assertEquals(200, result.getCode());
        verify(messageMapper).batchSave(list);
    }

    @Test
    void testBatchDelete() {
        List<Long> ids = Arrays.asList(1L, 2L);
        Result<Void> result = messageService.batchDelete(ids);
        assertEquals(200, result.getCode());
        verify(messageMapper).batchDelete(ids);
    }

    @Test
    void testQuery() {
        MessageQueryDto dto = new MessageQueryDto();
        List<MessageVO> voList = Arrays.asList(new MessageVO(), new MessageVO());
        when(messageMapper.query(dto)).thenReturn(voList);
        when(messageMapper.queryCount(dto)).thenReturn(2);
        Result<List<MessageVO>> result = messageService.query(dto);
        assertEquals(200, result.getCode());
        assertEquals(4, result.getMsg().length());
    }

    @Test
    void testSystemInfoUsersSave() {
        Message message = new Message();
        message.setContent("系统通知");
        User user1 = new User();
        user1.setId(1);
        user1.setUserRole(RoleEnum.USER.getRole());
        User user2 = new User();
        user2.setId(2);
        user2.setUserRole(RoleEnum.ADMIN.getRole());
        when(userMapper.query(any(UserQueryDto.class))).thenReturn(Arrays.asList(user1, user2));
        Result<Void> result = messageService.systemInfoUsersSave(message);
        assertEquals(200, result.getCode());
        verify(messageMapper).batchSave(any());
    }

    @Test
    void testClearMessage() {
        LocalThreadHolder.setUserId(1, RoleEnum.USER.getRole());
        Result<Void> result = messageService.clearMessage();
        assertEquals(200, result.getCode());
        verify(messageMapper).update(1, IsReadEnum.READ_OK.getStatus());
    }
}
