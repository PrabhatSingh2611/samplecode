package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookResourceEntity;
import digital.windmill.audra.graphql.type.PlaybookResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring",uses = {ResourceMapper.class}, imports = {UUID.class}
)
public interface PlaybookResourceMapper {

    @Mapping(target = "id", source = "uuid")
    PlaybookResource map(PlaybookResourceEntity entity);

}
