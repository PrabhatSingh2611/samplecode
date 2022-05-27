package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.graphql.facade.impl.PlaybookFacade;
import digital.windmill.audra.graphql.mapper.PlaybookMapper;
import digital.windmill.audra.graphql.type.Playbook;
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

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookFacadeTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private PlaybookService playbookService;
    @Mock
    private PlaybookMapper playbookMapper;
    @InjectMocks
    PlaybookFacade facade;

    @Test
    void shouldFindPlaybookByUuid(
            @Mock PlaybookEntity playbookEntity,
            @Mock Playbook playbook) {

        when(playbookService.findPlaybookByUuid(any(UUID.class))).thenReturn(playbookEntity);
        when(playbookMapper.map(any(PlaybookEntity.class)))
                .thenReturn(playbook);

        var result = facade.findPlaybookByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(playbook, result);
    }

    @Test
    void shouldGetPlaybooks(
            @Mock PlaybooksInput input,
            @Mock Playbook playbook,
            @Mock PlaybookEntity playbookEntity) {

        var pagedResponse = createOneItemPage(playbookEntity);
        when(playbookService.findAll(any(PlaybooksInput.class)))
                .thenReturn(pagedResponse);
        when(playbookMapper.map(any(PlaybookEntity.class)))
                .thenReturn(playbook);

        var result = facade.getPlaybooks(input);
        Assertions.assertEquals(new PageImpl<>(List.of(playbook)), result);
    }


    private <T> Page<T> createOneItemPage(T item) {

        return new PageImpl<>(List.of(item));
    }

}
