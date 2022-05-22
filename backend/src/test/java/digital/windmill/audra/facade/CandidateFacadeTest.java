package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.CandidateEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.graphql.facade.impl.CandidateFacadeImpl;
import digital.windmill.audra.graphql.mapper.CandidateMapper;
import digital.windmill.audra.graphql.type.Candidate;
import digital.windmill.audra.graphql.type.input.CandidatesInput;
import digital.windmill.audra.graphql.type.input.CreateCandidateInput;
import digital.windmill.audra.graphql.type.input.NodesInput;
import digital.windmill.audra.graphql.type.input.PatchCandidateInput;
import digital.windmill.audra.service.CandidateService;
import digital.windmill.audra.service.ResourceService;
import digital.windmill.audra.service.VacancyService;
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
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateFacadeTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final UUID VACANCY_UUID = UUID.randomUUID();
    private static final UUID RESOURCE_UUID = UUID.randomUUID();

    @Mock
    private CandidateService candidateService;

    @Mock
    private VacancyService vacancyService;

    @Mock
    private CandidateMapper candidateMapper;

    @Mock
    private ResourceService resourceService;

    @InjectMocks
    private CandidateFacadeImpl candidateFacade;

    @Test
    void shouldFindCandidateByUuid(@Mock CandidateEntity candidateEntity,
                                   @Mock Candidate candidate) {

        when(candidateService.findCandidateByUuid(any(UUID.class)))
                .thenReturn(candidateEntity);
        when(candidateMapper.mapCandidateEntityToCandidate(any(CandidateEntity.class)))
                .thenReturn(candidate);

        var result = candidateFacade.findCandidateByUuid(TEST_UUID);
        assertNotNull(result);
        assertSame(candidate, result);
    }

    @Test
    void shouldGetAllCandidates(@Mock CandidatesInput input,
                                @Mock CandidateEntity candidateEntity,
                                @Mock Candidate candidate) {

        Page<CandidateEntity> candidatePage = createOneItemPage(candidateEntity);
        when(candidateService.findAllCandidates(any(CandidatesInput.class)))
                .thenReturn(candidatePage);
        when(candidateMapper.mapCandidateEntityToCandidate(any(CandidateEntity.class)))
                .thenReturn(candidate);

        var result = candidateFacade.getCandidates(input);
        assertNotNull(result);
        assertEquals(candidatePage.getContent().get(0).getUuid(), result.getContent().get(0).getId());
        assertEquals(candidatePage.getContent().get(0).getFirstName(), result.getContent().get(0).getFirstName());
        assertEquals(candidatePage.getContent().get(0).getLastName(), result.getContent().get(0).getLastName());
        assertEquals(candidatePage.getContent().get(0).getLinkedIn(), result.getContent().get(0).getLinkedIn());
        assertEquals(candidatePage.getContent().get(0).getStatus(), result.getContent().get(0).getStatus());
        assertEquals(candidatePage.getContent().get(0).getAttachments(), result.getContent().get(0).getAttachments());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }

    @Test
    void shouldCreateCandidate(@Mock CreateCandidateInput input,
                               @Mock CandidateEntity candidateEntity,
                               @Mock Candidate candidate,
                               @Mock VacancyEntity vacancyEntity,
                               @Mock ResourceEntity resourceEntity) {

        when(input.getVacancy()).thenReturn(VACANCY_UUID);
        when(input.getAttachments()).thenReturn(new NodesInput(List.of(RESOURCE_UUID)));
        when(vacancyService.findVacancyByUuid(any(UUID.class))).thenReturn(vacancyEntity);
        when(resourceService.findByUuids((anyCollection()))).thenReturn(List.of(resourceEntity));
        when(candidateMapper.mapCreateCandidateInputToCandidateEntity(eq(input),
                eq(vacancyEntity),
                any(List.class))).thenReturn(candidateEntity);
        when(candidateMapper.mapCandidateEntityToCandidate(any(CandidateEntity.class))).thenReturn(candidate);
        when(candidateService.save(any(CandidateEntity.class))).thenReturn(candidateEntity);

        var result = candidateFacade.createCandidate(input);
        assertNotNull(result);
        assertSame(candidate, result);
    }

    @Test
    void shouldPatchCandidate(@Mock PatchCandidateInput input,
                               @Mock CandidateEntity candidateEntity,
                               @Mock Candidate candidate,
                               @Mock VacancyEntity vacancyEntity,
                               @Mock ResourceEntity resourceEntity) {

        when(input.getVacancy()).thenReturn(VACANCY_UUID);
        when(input.getId()).thenReturn(TEST_UUID);
        when(input.getAttachments()).thenReturn(new NodesInput(List.of(RESOURCE_UUID)));
        when(vacancyService.findVacancyByUuid(any(UUID.class)))
                .thenReturn(vacancyEntity);
        when(candidateService.findCandidateByUuid(any(UUID.class)))
                .thenReturn(candidateEntity);
        when(resourceService.findByUuids(anyCollection())).thenReturn((List.of(resourceEntity)));
        when(candidateMapper.mapPatchCandidateInputToCandidateEntity(
                eq(candidateEntity),
                eq(input),
                eq(vacancyEntity),
                any(List.class))).thenReturn(candidateEntity);
        when(candidateMapper.mapCandidateEntityToCandidate(any(CandidateEntity.class)))
                .thenReturn(candidate);
        when(candidateService.save(any(CandidateEntity.class)))
                .thenReturn(candidateEntity);

        var result = candidateFacade.patchCandidate(input);
        assertNotNull(result);
        assertSame(candidate, result);
    }




}
