package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.facade.CandidateFacade;
import digital.windmill.audra.graphql.mapper.CandidateMapper;
import digital.windmill.audra.graphql.type.Candidate;
import digital.windmill.audra.graphql.type.input.CandidatesInput;
import digital.windmill.audra.graphql.type.input.CreateCandidateInput;
import digital.windmill.audra.graphql.type.input.NodesInput;
import digital.windmill.audra.graphql.type.input.PatchCandidateInput;
import digital.windmill.audra.service.CandidateService;
import digital.windmill.audra.service.ResourceService;
import digital.windmill.audra.service.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CandidateFacadeImpl implements CandidateFacade {

    private CandidateService candidateService;
    private VacancyService vacancyService;
    private CandidateMapper candidateMapper;
    private ResourceService resourceService;

    @Transactional(readOnly = true)
    public Candidate findCandidateByUuid(UUID uuid) {
        return candidateMapper.mapCandidateEntityToCandidate(candidateService.findCandidateByUuid(uuid));
    }

    @Override
    @Transactional
    public Candidate createCandidate(CreateCandidateInput candidateInput) {
        var vacancyEntity = vacancyService.findVacancyByUuid(candidateInput.getVacancy());
        var resourceEntities = Optional.ofNullable(candidateInput.getAttachments()).map(NodesInput::getIds).map(resourceService::findByUuids).orElse(null);

        var candidateEntity = candidateMapper.mapCreateCandidateInputToCandidateEntity(candidateInput, vacancyEntity, resourceEntities);

        return candidateMapper.mapCandidateEntityToCandidate(candidateService.save(candidateEntity));
    }

    @Override
    @Transactional
    public Candidate patchCandidate(PatchCandidateInput patchCandidateInput) {
        var vacancyEntity = vacancyService.findVacancyByUuid(patchCandidateInput.getVacancy());
        var candidateEntity = candidateService.findCandidateByUuid(patchCandidateInput.getId());
        var resourceEntities = Optional.ofNullable(patchCandidateInput.getAttachments()).map(NodesInput::getIds).map(resourceService::findByUuids).orElse(null);

        candidateMapper.mapPatchCandidateInputToCandidateEntity(candidateEntity, patchCandidateInput, vacancyEntity, resourceEntities);

        return candidateMapper.mapCandidateEntityToCandidate(candidateService.save(candidateEntity));
    }

    @Transactional(readOnly = true)
    public Page<Candidate> getCandidates(CandidatesInput input) {
        return candidateService.findAllCandidates(input).map(candidateMapper::mapCandidateEntityToCandidate);
    }
}
