package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.input.CreatePlaybookInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {PlaybookSectionMapper.class, ResourceMapper.class, DateTimeMapper.class}, imports = {UUID.class})
public interface PlaybookMapper {

    @Mapping(target = "id", source = "uuid")
    Playbook map(PlaybookEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "image", source = "image")
    PlaybookEntity mapToCreate(CreatePlaybookInput input, ResourceEntity image);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "image", source = "image")
    PlaybookEntity mapToPatch(PatchPlaybookInput input,
                              @MappingTarget PlaybookEntity entity,
                              ResourceEntity image);
}
