package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookStepEntity;
import digital.windmill.audra.graphql.type.PlaybookStep;
import digital.windmill.i18n.mapper.I18nMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {PlaybookTaskListMapper.class, PlaybookVideoMapper.class, PlaybookResourceMapper.class, I18nMapper.class},
        imports = {UUID.class}
)
public interface PlaybookStepMapper {

    @Mapping(target = "id", source = "uuid")
    PlaybookStep map(PlaybookStepEntity entity);

}
