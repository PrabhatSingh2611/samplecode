package ch.windmill.audra.graphql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ch.windmill.audra.dao.entity.UserPositionEntity;
import ch.windmill.audra.graphql.type.UserPosition;

@Mapper(componentModel = "spring")
public interface UserPositionMapper {

    @Mapping(target = "id", source = "uuid")
    UserPosition map(UserPositionEntity entity);

}
