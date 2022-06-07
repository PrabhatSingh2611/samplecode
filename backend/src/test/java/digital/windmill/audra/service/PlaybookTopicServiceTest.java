package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionTopicEntity;
import digital.windmill.audra.dao.repository.PlaybookTopicRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.service.impl.PlaybookTopicService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookTopicServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    @Mock
    private PlaybookTopicRepository repository;

    @InjectMocks
    PlaybookTopicService service;

    @Test
    void shouldReturnPlaybookTopicByUuid(@Mock PlaybookSectionTopicEntity entity) {
        when(repository.findTopicByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(entity));

        var result = service.findTopicByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(entity, result);
    }

    @Test
    void shouldThrowDataNotFound() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findTopicByUuid(TEST_UUID));
    }

    @Test
    void shouldSave(@Mock PlaybookSectionTopicEntity playbookTopicEntity) {
        when(repository.save(playbookTopicEntity)).thenReturn(playbookTopicEntity);
        var actualResult = service.save(playbookTopicEntity);

        Assertions.assertEquals(playbookTopicEntity, actualResult);

    }

    @Test
    void shouldDelete(@Mock PlaybookSectionTopicEntity playbookTopicEntity) {
        service.delete(playbookTopicEntity);
        verify(repository).delete(playbookTopicEntity);
    }


    @Test
    void shouldGetNextSortPosition(@Mock PlaybookSectionEntity playbookSectionEntity) {
        when(repository.getLastPostPosition(playbookSectionEntity)).thenReturn(Optional.of(5));
        var actualResult = service.getNextSortPosition(playbookSectionEntity);
        Assertions.assertEquals(6, actualResult);
    }

    @Test
    void shouldGetNextSortPositionForFirstElement(@Mock PlaybookSectionEntity playbookSectionEntity) {
        when(repository.getLastPostPosition(playbookSectionEntity)).thenReturn(Optional.empty());
        var actualResult = service.getNextSortPosition(playbookSectionEntity);
        Assertions.assertEquals(1, actualResult);

    }

    @Test
    void shouldReorder(@Mock PlaybookSectionEntity playbookSectionEntity,
                       @Mock PlaybookSectionTopicEntity playbookTopicEntity,
                       @Mock PlaybookSectionTopicEntity afterPlaybookTopicEntity) {
        when(afterPlaybookTopicEntity.getSort()).thenReturn(1);
        service.reorder(playbookTopicEntity, afterPlaybookTopicEntity, playbookSectionEntity);
        verify(repository).shiftSort(playbookSectionEntity, 1);
        verify(repository).save(playbookTopicEntity);

    }
}
