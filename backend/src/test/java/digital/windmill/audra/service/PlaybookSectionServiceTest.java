package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.dao.repository.PlaybookSectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookSectionServiceTest {

    @InjectMocks
    private PlaybookSectionService playbookSectionService;

    @Mock
    private PlaybookSectionRepository playbookSectionRepository;

    @Test
    void shouldSave(@Mock PlaybookSectionEntity playbookSectionEntity) {
        when(playbookSectionRepository.save(playbookSectionEntity)).thenReturn(playbookSectionEntity);
        var actualResult = playbookSectionService.save(playbookSectionEntity);
        Assertions.assertEquals(playbookSectionEntity, actualResult);
    }

    @Test
    void shouldFindByUuid(@Mock PlaybookSectionEntity playbookSectionEntity) {
        var uuid = UUID.randomUUID();
        when(playbookSectionRepository.findOneByUuid(uuid)).thenReturn(Optional.of(playbookSectionEntity));
        var actualResult = playbookSectionService.findByUuid(uuid);
        Assertions.assertEquals(playbookSectionEntity, actualResult);
    }

    @Test
    void shouldDelete(@Mock PlaybookSectionEntity playbookSectionEntity) {
        playbookSectionService.delete(playbookSectionEntity);
        verify(playbookSectionRepository).delete(playbookSectionEntity);
    }

    @Test
    void shouldGetNextSortPosition(@Mock PlaybookEntity playbookEntity) {
        when(playbookSectionRepository.getLastSortPosition(playbookEntity)).thenReturn(Optional.of(5));
        var actualResult = playbookSectionService.getNextSortPosition(playbookEntity);
        Assertions.assertEquals(6, actualResult);
    }

    @Test
    void shouldGetNextSortPositionForFirstElement(@Mock PlaybookEntity playbookEntity) {
        when(playbookSectionRepository.getLastSortPosition(playbookEntity)).thenReturn(Optional.empty());
        var actualResult = playbookSectionService.getNextSortPosition(playbookEntity);
        Assertions.assertEquals(1, actualResult);
    }

    @Test
    void shouldReorder(@Mock PlaybookSectionEntity playbookSectionEntity,
                       @Mock PlaybookSectionEntity afterPlaybookSectionEntity) {
        when(afterPlaybookSectionEntity.getSort()).thenReturn(5);

        playbookSectionService.reorder(playbookSectionEntity, afterPlaybookSectionEntity);

        verify(playbookSectionRepository).shiftSort(playbookSectionEntity.getPlaybook(), 5);
        verify(playbookSectionEntity).setSort(6);
        verify(playbookSectionRepository).save(playbookSectionEntity);
    }
}