package digital.windmill.audra.facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import digital.windmill.audra.dao.entity.LeaveTypeEntity;
import digital.windmill.audra.graphql.facade.impl.LeaveTypeFacadeImpl;
import digital.windmill.audra.graphql.mapper.LeaveTypeMapper;
import digital.windmill.audra.graphql.type.LeaveType;
import digital.windmill.audra.graphql.type.input.LeaveTypesInput;
import digital.windmill.audra.service.LeaveTypeService;

@ExtendWith(MockitoExtension.class)
public class LeaveTypeFacadeTest {

    private static final UUID LEAVETYPE_UUID = UUID.randomUUID();

    @Mock
    private LeaveTypeService leaveTypeService;
    @Mock
    private LeaveTypeMapper leaveTypeMapper;
    @InjectMocks
    LeaveTypeFacadeImpl facade;

    @Test
    void shouldFindLeaveTypeByUuid(
            @Mock LeaveTypeEntity leaveTypeEntity,
            @Mock LeaveType leaveType) {

        when(leaveTypeService.findByUuid(any(UUID.class))).thenReturn(leaveTypeEntity);
        when(leaveTypeMapper.map(any(LeaveTypeEntity.class)))
                .thenReturn(leaveType);

        var result = facade.findByUuid(LEAVETYPE_UUID);
        assertNotNull(result);
        assertSame(leaveType, result);
    }

    @Test
    void shouldGetLeaveTypes(
            @Mock LeaveTypesInput input,
            @Mock LeaveType leaveType,
            @Mock LeaveTypeEntity leaveTypeEntity) {

        var pagedResponse = createOneItemPage(leaveTypeEntity);
        when(leaveTypeService.findAllLeaveTypes(input))
                .thenReturn(pagedResponse);
        when(leaveTypeMapper.map(any(LeaveTypeEntity.class)))
                .thenReturn(leaveType);

        var result = facade.getLeaveTypes(input);
        assertNotNull(result);
        assertSame(leaveType, result.getContent().get(0));
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
