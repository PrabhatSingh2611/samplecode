package digital.windmill.audra.graphql.resolver.employee;

import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.input.EmployeeInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeResolver implements GraphQLQueryResolver {

/*
    public ConnectionPayload<Employee> employees() {
        return ConnectionUtils.buildPayload(new PageImpl<>(List.of()));
    }
*/

    public ConnectionPayload<Employee> employees(EmployeeInput input) {
        return ConnectionUtils.buildPayload(new PageImpl<>(List.of()));
    }
}
