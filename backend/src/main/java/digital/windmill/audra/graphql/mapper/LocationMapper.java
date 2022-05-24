package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.Location;
import digital.windmill.audra.graphql.type.input.CreateLocationInput;
import digital.windmill.audra.graphql.type.input.UpdateLocationInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface LocationMapper {

    @Mapping(target = "id", source = "uuid")
    Location mapLocationEntityToLocation(LocationEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "country", source = "input.country")
    LocationEntity mapCreateLocationInputToLocationEntity(CreateLocationInput input);

    @Mapping(target = "id", ignore = true)
    LocationEntity mapLocationToLocationEntityUpdate(UpdateLocationInput input,
                                                     @MappingTarget LocationEntity updatedLocationEntity);

}
