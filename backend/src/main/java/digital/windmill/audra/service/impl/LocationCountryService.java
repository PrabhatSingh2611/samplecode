package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.LocationCountrySpecification;
import digital.windmill.audra.dao.entity.LocationCountryEntity;
import digital.windmill.audra.dao.repository.LocationCountryRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountriesInput;
import digital.windmill.audra.graphql.type.locationCountry.LocationCountrySort;
import digital.windmill.audra.graphql.utils.SortUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class LocationCountryService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final List<LocationCountrySort> DEFAULT_SORT = List.of(LocationCountrySort.name_ASC);

    private LocationCountryRepository repository;

    public LocationCountryEntity findByUuid(UUID uuid) {
        if (uuid == null) return null;

        return repository.findByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Location country not found " + uuid)
        );
    }

    public Page<LocationCountryEntity> findAll(LocationCountriesInput input) {
        var itemsPerPage = Optional.ofNullable(input.getPagination()).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input.getPagination()).map(PageInput::getPageNumber).orElse(DEFAULT_PAGE_NUMBER);
        var sortValues = Optional.ofNullable(input.getSort()).filter(e -> !e.isEmpty()).orElse(DEFAULT_SORT);
        var sort = sortValues.stream().map(e -> SortUtils.parse(e.name())).reduce(Sort::and).get();

        var spec = LocationCountrySpecification.all(input.getWhere());
        var page = PageRequest.of(pageNumber, itemsPerPage, sort);
        return repository.findAll(spec, page);
    }

    public LocationCountryEntity save(LocationCountryEntity entity) {
        return repository.save(entity);
    }

}
