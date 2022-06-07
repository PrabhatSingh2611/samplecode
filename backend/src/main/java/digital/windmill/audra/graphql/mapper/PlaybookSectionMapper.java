package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.graphql.type.PatchPlaybookSectionInput;
import digital.windmill.audra.graphql.type.PlaybookSection;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {PlaybookSectionTopicMapper.class}, imports = {UUID.class})
public interface PlaybookSectionMapper {
    @Mapping(target = "id", source = "uuid")
    PlaybookSection map(PlaybookSectionEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "playbook", source = "playbookEntity")
    @Mapping(target = "sort", source = "sort")
    PlaybookSectionEntity map(CreatePlaybookSectionInput input,
                              PlaybookEntity playbookEntity,
                              int sort);

    @Mapping(target = "id", ignore = true)
    PlaybookSectionEntity map(@MappingTarget PlaybookSectionEntity playbookSectionEntity, PatchPlaybookSectionInput input);
}
