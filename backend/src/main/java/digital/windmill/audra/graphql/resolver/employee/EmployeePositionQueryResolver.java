package digital.windmill.audra.graphql.resolver.employee;

import digital.windmill.audra.graphql.facade.EmployeePositionFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.EmployeePositionsInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeePositionQueryResolver implements GraphQLQueryResolver {

    private final EmployeePositionFacade employeePositionFacade;

    public ConnectionPayload<EmployeePosition> employeePositions(@NonNull EmployeePositionsInput input) {
        return ConnectionUtils.buildPayload(employeePositionFacade.getEmployeePositions(input));
    }
}