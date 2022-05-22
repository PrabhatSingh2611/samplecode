package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.CandidateEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.graphql.type.Candidate;
import digital.windmill.audra.graphql.type.input.CreateCandidateInput;
import digital.windmill.audra.graphql.type.input.PatchCandidateInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {VacancyMapper.class, ResourceMapper.class}, imports = {UUID.class})
public interface CandidateMapper {

    @Mapping(target = "id", source = "uuid")
    Candidate mapCandidateEntityToCandidate(CandidateEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "vacancy", source = "vacancyEntity")
    @Mapping(target = "firstName", source = "candidateInput.firstName")
    @Mapping(target = "lastName", source = "candidateInput.lastName")
    @Mapping(target = "linkedIn", source = "candidateInput.linkedIn")
    @Mapping(target = "attachments", source = "resourceEntities")
    @Mapping(target = "status", source = "candidateInput.status")
    CandidateEntity mapCreateCandidateInputToCandidateEntity(CreateCandidateInput candidateInput,
                                                             VacancyEntity vacancyEntity,
                                                             List<ResourceEntity> resourceEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "vacancy", source = "vacancyEntity")
    @Mapping(target = "firstName", source = "candidateInput.firstName")
    @Mapping(target = "lastName", source = "candidateInput.lastName")
    @Mapping(target = "linkedIn", source = "candidateInput.linkedIn")
    @Mapping(target = "attachments", source = "resourceEntities")
    @Mapping(target = "status", source = "candidateInput.status")
    CandidateEntity mapPatchCandidateInputToCandidateEntity(@MappingTarget CandidateEntity candidateEntity,
                                                            PatchCandidateInput candidateInput,
                                                            VacancyEntity vacancyEntity,
                                                            List<ResourceEntity> resourceEntities
    );

}
