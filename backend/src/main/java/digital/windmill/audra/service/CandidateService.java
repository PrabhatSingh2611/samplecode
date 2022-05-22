package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.CandidateEntity;
import digital.windmill.audra.graphql.type.input.CandidatesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CandidateService {

    CandidateEntity findCandidateByUuid(UUID uuid);

    CandidateEntity save(CandidateEntity candidateEntity);

    Page<CandidateEntity> findAllCandidates(CandidatesInput input);
}
