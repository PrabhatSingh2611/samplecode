package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.LocationCountryEntity;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.graphql.type.location.CreateLocationInput;
import digital.windmill.audra.graphql.type.location.Location;
import digital.windmill.audra.graphql.type.location.UpdateLocationInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {LocationCountryMapper.class},
        imports = {UUID.class})
public interface LocationMapper {

    @Mapping(target = "id", source = "uuid")
    Location map(LocationEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "country", source = "country")
    LocationEntity map(CreateLocationInput input,
                       LocationCountryEntity country);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "country", source = "country")
    LocationEntity map(UpdateLocationInput input,
                       @MappingTarget LocationEntity updatedLocationEntity,
                       LocationCountryEntity country);

}
