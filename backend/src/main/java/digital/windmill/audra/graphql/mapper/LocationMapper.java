package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface LocationMapper {


    /**
     * @param entity it takes LocationEntity as Input
     * @return mapped to Location
     */
    Location mapLocationEntityToLocation(LocationEntity entity);


    /**
     * @param input it takes CreateLocationInput as Input
     * @return mapped to LocationEntity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(generateUUID())")
    @Mapping(target = "name", source = "input.name")
    LocationEntity mapCreateLocationInputToLocationEntity(CreateLocationInput input);

    default UUID generateUUID() {
        return UUID.randomUUID();
    }

    LocationEntity mapLocationToLocationEntity(Location location);
}
