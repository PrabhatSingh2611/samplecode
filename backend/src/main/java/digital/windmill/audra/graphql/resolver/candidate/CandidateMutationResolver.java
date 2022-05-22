package digital.windmill.audra.graphql.resolver.candidate;

import digital.windmill.audra.graphql.facade.CandidateFacade;
import digital.windmill.audra.graphql.type.CandidatePayload;
import digital.windmill.audra.graphql.type.input.CreateCandidateInput;
import digital.windmill.audra.graphql.type.input.PatchCandidateInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CandidateMutationResolver implements GraphQLMutationResolver {

    private CandidateFacade candidateFacade;

    public CandidatePayload createCandidate(CreateCandidateInput candidateInput) {
        return CandidatePayload
                .builder()
                .candidate(candidateFacade.createCandidate(candidateInput))
                .build();
    }

    public CandidatePayload patchCandidate(PatchCandidateInput candidateInput) {
        return CandidatePayload
                .builder()
                .candidate(candidateFacade.patchCandidate(candidateInput))
                .build();
    }
}
