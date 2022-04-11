package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    Location map(LocationEntity entity);

}
