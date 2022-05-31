package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.LocationCountryEntity;
import digital.windmill.audra.graphql.type.locationCountry.CreateLocationCountryInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
import digital.windmill.audra.graphql.type.locationCountry.PatchLocationCountryInput;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {LocationMapper.class},
        imports = {UUID.class})
public interface LocationCountryMapper {

    @Mapping(target = "id", source = "uuid")
    LocationCountry map(LocationCountryEntity entity);

    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    LocationCountryEntity map(CreateLocationCountryInput input);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    LocationCountryEntity map(PatchLocationCountryInput input, @MappingTarget LocationCountryEntity country);

}
