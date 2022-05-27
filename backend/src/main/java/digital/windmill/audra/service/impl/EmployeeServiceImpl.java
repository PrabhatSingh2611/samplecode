package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.EmployeeSpecification;
import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.repository.EmployeeRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity findEmployeeByUuid(UUID uuid) {
        if (Objects.nonNull(uuid)) {
            return employeeRepository.findByUuid(uuid).orElseThrow(
                    () -> new DataNotFoundException("Employee not found for : " + uuid)
            );
        }
        return null;
    }

    @Override
    public Page<EmployeeEntity> getEmployees(EmployeesInput input) {
        var spec = EmployeeSpecification.employees(input.getWhere(),input.getSort());
        var itemsPerPage = Optional.of(input).map(EmployeesInput::getPagination).
                map(PageInput::getItemsPerPage).orElse(PageInput.MAX_ITEMS_PER_PAGE);
        var pageNumber = Optional.of(input).map(EmployeesInput::getPagination).
                map(PageInput::getPageNumber).orElse(0);
        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return employeeRepository.findAll(spec, pageable);
    }
}
