package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.CandidateFacade;
import digital.windmill.audra.graphql.resolver.candidate.CandidateResolver;
import digital.windmill.audra.graphql.type.Candidate;
import digital.windmill.audra.graphql.type.input.CandidateInput;
import digital.windmill.audra.graphql.type.input.CandidatesInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CandidateResolverTest {

    private static final UUID TEST_UUID = UUID.randomUUID();

    @Mock
    private CandidateFacade candidateFacade;

    @InjectMocks
    private CandidateResolver candidateResolver;

    @Test
    void shouldGetCandidate(@Mock CandidateInput input,
                            @Mock Candidate candidate) {

        when(input.getId()).thenReturn(TEST_UUID);
        when(candidateFacade.findCandidateByUuid(any(UUID.class)))
                .thenReturn(candidate);

        var result = candidateResolver.candidate(input);
        assertNotNull(result);
        assertSame(candidate, result.getCandidate());
    }

    @Test
    void shouldGetAllCandidates(@Mock CandidatesInput input,
                                @Mock Candidate candidate) {

        Page<Candidate> pagedResponse = createOneItemPage(candidate);
        when(candidateFacade.getCandidates(any(CandidatesInput.class)))
                .thenReturn(pagedResponse);

        var result = candidateResolver.candidates(input);
        assertNotNull(result);
        assertEquals(pagedResponse.getContent(), result.getItems());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}