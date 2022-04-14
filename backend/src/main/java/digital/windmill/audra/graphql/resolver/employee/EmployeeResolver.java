package digital.windmill.audra.graphql.resolver.employee;

import digital.windmill.audra.graphql.facade.impl.EmployeeFacadeImpl;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.EmployeesInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeResolver implements GraphQLQueryResolver {

    private EmployeeFacadeImpl employeeFacadeImpl;

    public ConnectionPayload<Employee> employees(EmployeesInput input) {
        return ConnectionUtils.buildPayload(employeeFacadeImpl.getEmployees(input));
    }
}