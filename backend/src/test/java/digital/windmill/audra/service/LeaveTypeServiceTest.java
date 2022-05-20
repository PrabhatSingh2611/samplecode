package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.AnnouncementEntity;
import digital.windmill.audra.dao.entity.LeaveTypeEntity;
import digital.windmill.audra.dao.repository.LeaveTypeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import digital.windmill.audra.graphql.type.input.LeaveTypesInput;
import digital.windmill.audra.service.impl.LeaveTypeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeaveTypeServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private LeaveTypeRepository leaveTypeRepository;

    @InjectMocks
    LeaveTypeServiceImpl service;

    @Test
    void shouldReturnLeaveTypeByUuid(@Mock LeaveTypeEntity leaveTypeEntity) {
        when(leaveTypeRepository.findByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(leaveTypeEntity));

        var result = service.findByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(leaveTypeEntity, result);
    }

    @Test
    void shouldThrowDataNotFound() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnAllLeaveTypes(
            @Mock LeaveTypesInput input,
            @Mock LeaveTypeEntity leaveTypeEntity
            ) {
        var leaveTypeEntityPage = createOneItemPage(leaveTypeEntity);
        when(leaveTypeRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(leaveTypeEntityPage);
        var result = service.findAllLeaveTypes(input);
        assertNotNull(result);
        assertSame(leaveTypeEntityPage, result);
    }
    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

}
