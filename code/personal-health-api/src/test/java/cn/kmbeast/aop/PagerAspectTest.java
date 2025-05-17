package cn.kmbeast.aop;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.aop.PagerAspect;
import cn.kmbeast.pojo.dto.query.base.QueryDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class PagerAspectTest {

    private final PagerAspect pagerAspect = new PagerAspect();

    @Test
    void testHandlePageableParams_ValidCurrentAndSize() throws Throwable {
        QueryDto queryDto = new QueryDto();
        queryDto.setCurrent(2);
        queryDto.setSize(10);

        ProceedingJoinPoint joinPoint = mockJoinPointWithArgs(queryDto);

        pagerAspect.handlePageableParams(joinPoint, mock(Pager.class));

        assertProceedArgs(joinPoint, 10);
    }

    @Test
    void testHandlePageableParams_CurrentIsNull() throws Throwable {
        QueryDto queryDto = new QueryDto();
        queryDto.setCurrent(null);
        queryDto.setSize(10);

        ProceedingJoinPoint joinPoint = mockJoinPointWithArgs(queryDto);

        pagerAspect.handlePageableParams(joinPoint, mock(Pager.class));

        assertProceedArgs(joinPoint, null);
    }

    @Test
    void testHandlePageableParams_SizeIsNull() throws Throwable {
        QueryDto queryDto = new QueryDto();
        queryDto.setCurrent(3);
        queryDto.setSize(null);

        ProceedingJoinPoint joinPoint = mockJoinPointWithArgs(queryDto);

        pagerAspect.handlePageableParams(joinPoint, mock(Pager.class));

        assertProceedArgs(joinPoint, 3);
    }

    @Test
    void testHandlePageableParams_NonQueryDtoArgument() throws Throwable {
        Object nonQueryDtoArg = new Object();
        ProceedingJoinPoint joinPoint = mockJoinPointWithArgs(nonQueryDtoArg);

        pagerAspect.handlePageableParams(joinPoint, mock(Pager.class));

        ArgumentCaptor<Object[]> argsCaptor = ArgumentCaptor.forClass(Object[].class);
        verify(joinPoint).proceed(argsCaptor.capture());
        assertEquals(nonQueryDtoArg, argsCaptor.getValue()[0]);
    }

    @Test
    void testHandlePageableParams_MultipleQueryDtoArgs() throws Throwable {
        QueryDto dto1 = new QueryDto();
        dto1.setCurrent(2);
        dto1.setSize(10);
        QueryDto dto2 = new QueryDto();
        dto2.setCurrent(3);
        dto2.setSize(20);

        ProceedingJoinPoint joinPoint = mockJoinPointWithArgs(dto1, new Object(), dto2);

        pagerAspect.handlePageableParams(joinPoint, mock(Pager.class));

        ArgumentCaptor<Object[]> argsCaptor = ArgumentCaptor.forClass(Object[].class);
        verify(joinPoint).proceed(argsCaptor.capture());
        Object[] args = argsCaptor.getValue();

        assertEquals(3, args.length);
        assertEquals(10, ((QueryDto) args[0]).getCurrent()); // (2-1)*10=10
        assertEquals(40, ((QueryDto) args[2]).getCurrent()); // (3-1)*20=40
    }

    // 辅助方法：模拟包含指定参数的ProceedingJoinPoint
    private ProceedingJoinPoint mockJoinPointWithArgs(Object... args) {
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        when(joinPoint.getArgs()).thenReturn(args);
        return joinPoint;
    }

    // 辅助方法：验证proceed的参数中的QueryDto.current值
    private void assertProceedArgs(ProceedingJoinPoint joinPoint, Integer expectedCurrent) throws Throwable {
        ArgumentCaptor<Object[]> argsCaptor = ArgumentCaptor.forClass(Object[].class);
        verify(joinPoint).proceed(argsCaptor.capture());

        Object[] args = argsCaptor.getValue();
        QueryDto dto = (QueryDto) args[0];
        if (expectedCurrent == null) {
            assertNull(dto.getCurrent());
        } else {
            assertEquals(expectedCurrent, dto.getCurrent());
        }
    }
}