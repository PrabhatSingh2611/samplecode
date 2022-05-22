package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.Candidate;
import digital.windmill.audra.graphql.type.input.CandidatesInput;
import digital.windmill.audra.graphql.type.input.CreateCandidateInput;
import digital.windmill.audra.graphql.type.input.PatchCandidateInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CandidateFacade {

    Candidate findCandidateByUuid(UUID uuid);

    Candidate createCandidate(CreateCandidateInput candidateInput);

    Candidate patchCandidate(PatchCandidateInput input);

    Page<Candidate> getCandidates(CandidatesInput input);
}

