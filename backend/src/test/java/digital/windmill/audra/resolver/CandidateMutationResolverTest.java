package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.CandidateFacade;
import digital.windmill.audra.graphql.resolver.candidate.CandidateMutationResolver;
import digital.windmill.audra.graphql.type.Candidate;
import digital.windmill.audra.graphql.type.input.CreateCandidateInput;
import digital.windmill.audra.graphql.type.input.PatchCandidateInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CandidateMutationResolverTest {

    @Mock
    private CandidateFacade candidateFacade;

    @InjectMocks
    private CandidateMutationResolver candidateMutationResolver;

    @Test
    void shouldCreateCandidate(@Mock CreateCandidateInput input,
                               @Mock Candidate candidate) {

        when(candidateFacade.createCandidate(any(CreateCandidateInput.class)))
                .thenReturn(candidate);

        var result = candidateMutationResolver.createCandidate(input);
        assertNotNull(result);
        assertSame(candidate, result.getCandidate());
    }

    @Test
    void shouldPatchCandidate(@Mock PatchCandidateInput input,
                               @Mock Candidate candidate) {

        when(candidateFacade.patchCandidate(any(PatchCandidateInput.class)))
                .thenReturn(candidate);

        var result = candidateMutationResolver.patchCandidate(input);
        assertNotNull(result);
        assertSame(candidate, result.getCandidate());
    }
}