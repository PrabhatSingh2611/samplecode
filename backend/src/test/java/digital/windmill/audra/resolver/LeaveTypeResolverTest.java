package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.LeaveTypeFacade;
import digital.windmill.audra.graphql.resolver.leaveType.LeaveTypeResolver;
import digital.windmill.audra.graphql.type.LeaveType;
import digital.windmill.audra.graphql.type.input.LeaveTypesInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeaveTypeResolverTest {
    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private LeaveTypeFacade leaveTypeFacade;

    @InjectMocks
    LeaveTypeResolver resolver;

    @Test
    void shouldGetLeaveTypes(
            @Mock LeaveTypesInput input,
            @Mock LeaveType leaveType
    ) {
        var pagedLeaveType = createOneItemPage(leaveType);
        when(leaveTypeFacade.getLeaveTypes(input))
                .thenReturn(pagedLeaveType);

        var result = resolver.leaveTypes(input);
        assertNotNull(result);
        assertSame(leaveType, result.getItems().get(0));
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }


}
