package digital.windmill.audra.graphql.resolver.employee;

import digital.windmill.audra.graphql.type.Employee;
import digital.windmill.audra.graphql.type.ExtendedConnection;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeResolver implements GraphQLQueryResolver {

    public ExtendedConnection<Employee> employees() {
        return ConnectionUtils.buildNodeConnection(new PageImpl<>(List.of()));
    }
}
