package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.type.Policy;
import digital.windmill.audra.graphql.type.input.CreatePolicyInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class, ResourceMapper.class, EmployeeMapper.class},
        imports = {UUID.class, Instant.class})
public interface PolicyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "title", source = "input.title")
    @Mapping(target = "file", source = "file")
    @Mapping(target = "publicationDate", expression = "java(Instant.now())")
    @Mapping(target = "status", source = "input.status")
    @Mapping(target = "owner", source = "owner")
    PolicyEntity mapCreatePolicyInputToPolicyEntity(EmployeeEntity owner,
                                                    ResourceEntity file,
                                                    CreatePolicyInput input);

    @Mapping(target = "id", source = "uuid")
    Policy mapPolicyEntityToPolicy(PolicyEntity savedPolicy);
}