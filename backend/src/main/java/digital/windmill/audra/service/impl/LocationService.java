package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.LocationSpecification;
import digital.windmill.audra.dao.entity.LocationEntity;
import digital.windmill.audra.dao.repository.LocationRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.type.location.LocationSort;
import digital.windmill.audra.graphql.type.location.LocationsInput;
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
public class LocationService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final List<LocationSort> DEFAULT_SORT = List.of(LocationSort.name_ASC);

    private LocationRepository locationRepository;

    public LocationEntity findLocationByUuid(UUID uuid) {
        if (Optional.ofNullable(uuid).isPresent()) {
            return locationRepository.findByUuid(uuid).orElseThrow(
                    () -> new DataNotFoundException("location not found for : " + uuid)
            );
        } else
            return null;
    }

    public LocationEntity save(LocationEntity locationEntity) {
        return locationRepository.save(locationEntity);
    }

    public Page<LocationEntity> getLocations(LocationsInput input) {
        var itemsPerPage = Optional.ofNullable(input.getPagination()).map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);
        var pageNumber = Optional.ofNullable(input.getPagination()).map(PageInput::getPageNumber).orElse(DEFAULT_PAGE_NUMBER);
        var sortValues = Optional.ofNullable(input.getSort()).filter(e -> !e.isEmpty()).orElse(DEFAULT_SORT);
        var sort = sortValues.stream().map(e -> SortUtils.parse(e.name())).reduce(Sort::and).get();

        var spec = LocationSpecification.all(input.getWhere());
        var page = PageRequest.of(pageNumber, itemsPerPage, sort);
        return locationRepository.findAll(spec, page);
    }

}
