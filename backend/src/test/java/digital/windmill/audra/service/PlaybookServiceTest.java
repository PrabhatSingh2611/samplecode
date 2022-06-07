package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.repository.PlaybookRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.PlaybooksInput;
import digital.windmill.audra.service.impl.PlaybookService;
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
class PlaybookServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    @Mock
    private PlaybookRepository playbookRepository;

    @InjectMocks
    PlaybookService service;

    @Test
    void shouldReturnPlaybookByUuid(@Mock PlaybookEntity playbookEntity) {
        when(playbookRepository.findPlaybookByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(playbookEntity));

        var result = service.findPlaybookByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(playbookEntity, result);
    }

    @Test
    void shouldThrowDataNotFound() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findPlaybookByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnAllPlaybooks(@Mock PlaybooksInput input, @Mock PlaybookEntity playbookEntity) {
        var playbookEntityPage = createOneItemPage(playbookEntity);
        when(playbookRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(playbookEntityPage);
        var result = service.findAll(input);
        assertNotNull(result);
        assertSame(playbookEntityPage, result);
    }

    @Test
    void shouldSave(@Mock PlaybookEntity playbookEntity) {
        when(playbookRepository.save(any(PlaybookEntity.class))).thenReturn(playbookEntity);

        var result = service.save(playbookEntity);
        assertNotNull(result);
        assertSame(playbookEntity, result);
    }

    @Test
    void shouldDeleteSurvey(@Mock PlaybookEntity playbookEntity) {
        var result = service.deletePlaybook(playbookEntity);
        assertNotNull(result);
        assertSame(playbookEntity, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}
