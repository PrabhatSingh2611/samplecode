package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EmployeeFacade {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public Page<Employee> getEmployees(EmployeesInput input) {
        return employeeService.findAll(input)
                .map(employeeMapper::map);
    }
}
