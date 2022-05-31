package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.graphql.mapper.LocationCountryMapper;
import digital.windmill.audra.graphql.type.locationCountry.CreateLocationCountryInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountriesInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountry;
import digital.windmill.audra.graphql.type.locationCountry.PatchLocationCountryInput;
import digital.windmill.audra.service.impl.LocationCountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LocationCountryFacade {

    private LocationCountryService locationCountryService;
    private LocationCountryMapper locationCountryMapper;

    @Transactional(readOnly = true)
    public Page<LocationCountry> findLocationCountries(LocationCountriesInput input) {
        return locationCountryService.findAll(input)
                .map(locationCountryMapper::map);
    }

    @Transactional
    public LocationCountry createLocationCountry(CreateLocationCountryInput input) {
        var savedCountry = locationCountryMapper.map(input);
        return locationCountryMapper.map(locationCountryService.save(savedCountry));
    }

    @Transactional
    public LocationCountry patchLocationCountry(PatchLocationCountryInput input) {
        var country = locationCountryService.findByUuid(input.getId());
        var patchedCountry = locationCountryMapper.map(input, country);
        return locationCountryMapper.map(locationCountryService.save(patchedCountry));
    }
}