package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.CandidateSpecification;
import digital.windmill.audra.dao.entity.CandidateEntity;
import digital.windmill.audra.dao.repository.CandidateRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.CandidatesInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private CandidateRepository candidateRepository;

    @Override
    public CandidateEntity findCandidateByUuid(UUID uuid) {
        return candidateRepository.findCandidateByUuid(uuid).orElseThrow(() -> new DataNotFoundException("Candidate not available for given UUID : " + uuid.toString()));
    }

    @Override
    public CandidateEntity save(CandidateEntity candidateEntity) {
        return candidateRepository.save(candidateEntity);
    }

    @Override
    public Page<CandidateEntity> findAllCandidates(CandidatesInput input) {
        var specification = CandidateSpecification.byCandidatesInput(input);
        var itemsPerPage = Optional.ofNullable(input).map(CandidatesInput::getPagination).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input).map(CandidatesInput::getPagination).map(PageInput::getPageNumber).orElse(0);
        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return candidateRepository.findAll(specification, pageable);
    }
}
