package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.i18n.mapper.I18nMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {DateTimeMapper.class,
                ResourceMapper.class,
                PlaybookStepMapper.class,
                I18nMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = {UUID.class}
)
public interface PlaybookMapper {

    @Mapping(target = "id", source = "uuid")
    Playbook map(PlaybookEntity entity);

}
