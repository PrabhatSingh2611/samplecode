package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.OptionEntity;
import digital.windmill.audra.dao.entity.QuestionEntity;
import digital.windmill.audra.dao.repository.OptionRepository;
import digital.windmill.audra.dao.repository.QuestionRepository;
import digital.windmill.audra.service.impl.OptionServiceImpl;
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
class OptionServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private OptionRepository repository;
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    OptionServiceImpl service;


    @Test
    void shouldReturnAllOptions(@Mock OptionEntity optionEntity,
                                @Mock QuestionEntity questionEntity) {
        when(questionRepository.findQuestionByUuid(any(UUID.class))).thenReturn(Optional.of(questionEntity));
        var optionEntityPage = createOneItemPage(optionEntity);
        when(repository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(optionEntityPage);
        var result = service.findAllOptions(TEST_UUID);
        assertNotNull(result);
        assertSame(optionEntityPage, result);
    }


    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}
