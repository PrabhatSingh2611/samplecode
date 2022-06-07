package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionTopicEntity;
import digital.windmill.audra.graphql.type.PlaybookSectionTopic;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookSectionTopicInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {PlaybookSectionMapper.class,
                ResourceMapper.class,
                DateTimeMapper.class},
        imports = {UUID.class})
public interface PlaybookTopicMapper {

    @Mapping(target = "id", source = "uuid")
    PlaybookSectionTopic map(PlaybookSectionTopicEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "section", source = "playbookSectionEntity")
    @Mapping(target = "sort", source = "sort")
    PlaybookSectionTopicEntity map(CreatePlaybookSectionTopicInput input,
                                   PlaybookSectionEntity playbookSectionEntity,
                                   int sort);

    @Mapping(target = "id", ignore = true)
    PlaybookSectionTopicEntity map(@MappingTarget PlaybookSectionTopicEntity playbookTopicEntity,
                                   PatchPlaybookSectionTopicInput input);
}
