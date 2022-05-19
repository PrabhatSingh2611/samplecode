package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.OptionEntity;
import digital.windmill.audra.graphql.facade.impl.OptionFacadeImpl;
import digital.windmill.audra.graphql.mapper.OptionMapper;
import digital.windmill.audra.graphql.type.QuestionOption;
import digital.windmill.audra.service.OptionService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OptionFacadeTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private OptionService service;
    @Mock
    private OptionMapper optionMapper;
    @InjectMocks
    OptionFacadeImpl facade;


    @Test
    void shouldGetOptions(
            @Mock QuestionOption option,
            @Mock OptionEntity optionEntity) {

        var pagedResponse = createOneItemPage(optionEntity);
        when(service.findAllOptions(any(UUID.class)))
                .thenReturn(pagedResponse);
        when(optionMapper.mapOptionEntityToOption(any(OptionEntity.class)))
                .thenReturn(option);

        var result = facade.getOptions(TEST_UUID);

        Assertions.assertEquals(new PageImpl<>(List.of(option)), result);
    }

    private <T> Page<T> createOneItemPage(T item) {

        return new PageImpl<>(List.of(item));
    }

}
