package digital.windmill.audra.graphql.resolver.candidate;

import digital.windmill.audra.graphql.facade.CandidateFacade;
import digital.windmill.audra.graphql.type.Candidate;
import digital.windmill.audra.graphql.type.CandidatePayload;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.input.CandidateInput;
import digital.windmill.audra.graphql.type.input.CandidatesInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CandidateResolver implements GraphQLQueryResolver {

    private CandidateFacade candidateFacade;

    public CandidatePayload candidate(CandidateInput input) {
        return CandidatePayload
                .builder()
                .candidate(candidateFacade.findCandidateByUuid(input.getId()))
                .build();
    }

    public ConnectionPayload<Candidate> candidates(CandidatesInput input) {
        return ConnectionUtils.buildPayload(candidateFacade.getCandidates(input));
    }
}
