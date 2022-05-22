package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.CandidateEntity;
import digital.windmill.audra.dao.repository.CandidateRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.CandidatesInput;
import digital.windmill.audra.service.impl.CandidateServiceImpl;
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
public class CandidateServiceTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @Test
    void shouldReturnCandidateByUuid(@Mock CandidateEntity candidateEntity) {

        when(candidateRepository.findCandidateByUuid(any(UUID.class)))
                .thenReturn(Optional.ofNullable(candidateEntity));

        var result = candidateService.findCandidateByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(candidateEntity, result);
    }

    @Test
    void shouldThrowDataNotFoundWhenUuidIsNull() {
        when(candidateRepository.findCandidateByUuid(any(UUID.class)))
                .thenThrow(new DataNotFoundException("Candidate not found for :" + TEST_UUID));
        Assertions.assertThrows(DataNotFoundException.class, () -> candidateService.findCandidateByUuid(TEST_UUID));
    }

    @Test
    void shouldReturnAllCandidate(@Mock CandidatesInput input,
                                  @Mock CandidateEntity candidateEntity) {

        var candidateEntityPage = createOneItemPage(candidateEntity);
        when(candidateRepository.findAll(any(Specification.class), any(PageRequest.class)))
                .thenReturn(candidateEntityPage);

        var result = candidateService.findAllCandidates(input);
        assertNotNull(result);
        assertSame(candidateEntityPage, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

    @Test
    void shouldCreateCandidate(@Mock CandidateEntity candidateEntity) {
        when(candidateRepository.save(any(CandidateEntity.class))).thenReturn(candidateEntity);

        var result = candidateService.save(candidateEntity);
        assertNotNull(result);
        assertSame(candidateEntity, result);
    }

    @Test
    void shouldPatchCandidate(@Mock CandidateEntity candidateEntity) {
        when(candidateRepository.save(any(CandidateEntity.class))).thenReturn(candidateEntity);

        var result = candidateService.save(candidateEntity);
        assertNotNull(result);
        assertSame(candidateEntity, result);
    }

}
