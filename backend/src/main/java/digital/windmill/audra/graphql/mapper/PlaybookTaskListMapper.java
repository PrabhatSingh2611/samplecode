package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookTaskListEntity;
import digital.windmill.audra.graphql.type.PlaybookTaskList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {ResourceMapper.class, PlaybookTaskMapper.class},
        imports = {UUID.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PlaybookTaskListMapper {

    @Mapping(target = "id", source = "uuid")
    PlaybookTaskList map(PlaybookTaskListEntity entity);

}
