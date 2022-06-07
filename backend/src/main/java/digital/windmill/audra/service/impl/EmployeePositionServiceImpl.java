package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.dao.repository.EmployeePositionRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.enums.EmployeePositionsSort;
import digital.windmill.audra.graphql.type.input.EmployeePositionsInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.utils.SortUtils;
import digital.windmill.audra.service.EmployeePositionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeePositionServiceImpl implements EmployeePositionService {

    private EmployeePositionRepository employeePositionRepository;

    @Override
    public EmployeePositionEntity save(EmployeePositionEntity employeePositionEntity) {
        return employeePositionRepository.save(employeePositionEntity);
    }

    @Override
    public EmployeePositionEntity deleteEmployeePosition(EmployeePositionEntity employeePositionEntity) {
        employeePositionRepository.delete(employeePositionEntity);
        return employeePositionEntity;
    }

    @Override
    public EmployeePositionEntity findEmployeePositionByUuid(UUID uuid) {
        if (uuid == null) return null;
        return employeePositionRepository.findByUuid(uuid)
                .orElseThrow(() -> new DataNotFoundException("Employee Position not found for : " + uuid.toString()));
    }

    @Override
    public Page<EmployeePositionEntity> findAll(@NonNull EmployeePositionsInput input) {
        var itemsPerPage = Optional.of(input)
                .map(EmployeePositionsInput::getPagination)
                .map(PageInput::getItemsPerPage)
                .orElse(PageInput.MAX_ITEMS_PER_PAGE);

        var pageNumber = Optional.of(input)
                .map(EmployeePositionsInput::getPagination)
                .map(PageInput::getPageNumber)
                .orElse(0);

        var sort = Optional.of(input)
                .map(EmployeePositionsInput::getSort)
                .stream()
                .flatMap(Collection::stream)
                .map(EmployeePositionsSort::name)
                .map(SortUtils::parse)
                .reduce(Sort::and)
                .orElse(Sort.by("name").ascending());

        var pageRequest = PageRequest.of(pageNumber, itemsPerPage, sort);

        return employeePositionRepository.findAll(pageRequest);
    }
}
