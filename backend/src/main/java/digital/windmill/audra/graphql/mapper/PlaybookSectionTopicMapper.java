package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookSectionTopicEntity;
import digital.windmill.audra.graphql.type.PlaybookSectionTopic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaybookSectionTopicMapper {
    @Mapping(target = "id", source = "uuid")
    PlaybookSectionTopic map(PlaybookSectionTopicEntity entity);
}
