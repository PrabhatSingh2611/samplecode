package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookTaskEntity;
import digital.windmill.audra.graphql.type.PlaybookTask;
import digital.windmill.i18n.mapper.I18nMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {I18nMapper.class},
        imports = {UUID.class}
)
public interface PlaybookTaskMapper {

    @Mapping(target = "id", source = "uuid")
    PlaybookTask map(PlaybookTaskEntity entity);

}
