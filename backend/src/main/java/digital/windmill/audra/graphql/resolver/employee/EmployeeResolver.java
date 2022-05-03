package digital.windmill.audra.graphql.resolver.employee;

import digital.windmill.audra.graphql.facade.EmployeeFacade;
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

    private EmployeeFacade employeeFacade;

    public ConnectionPayload<Employee> employees(EmployeesInput input) {
        return ConnectionUtils.buildPayload(employeeFacade.getEmployees(input));
    }
}