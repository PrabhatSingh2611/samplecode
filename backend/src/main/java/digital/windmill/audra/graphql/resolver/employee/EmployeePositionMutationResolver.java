package digital.windmill.audra.graphql.resolver.employee;

import digital.windmill.audra.graphql.facade.EmployeePositionFacade;
import digital.windmill.audra.graphql.type.DeleteEmployeePositionPayload;
import digital.windmill.audra.graphql.type.EmployeePositionPayload;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.DeleteEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeePositionMutationResolver implements GraphQLMutationResolver {

    private EmployeePositionFacade employeePositionFacade;

    public EmployeePositionPayload createEmployeePosition(CreateEmployeePositionInput createEmployeePositionInput)
    {
       return  EmployeePositionPayload
               .builder()
               .item(employeePositionFacade.createEmployeePosition(createEmployeePositionInput))
               .build();
    }

    public EmployeePositionPayload updateEmployeePosition(UpdateEmployeePositionInput input){
        return EmployeePositionPayload
                .builder()
                .item(employeePositionFacade.updateEmployeePosition(input))
                .build();
    }

    public DeleteEmployeePositionPayload deleteEmployeePosition(DeleteEmployeePositionInput input) {
        return DeleteEmployeePositionPayload
                .builder()
                .employeePosition(employeePositionFacade.deleteEmployeePosition(input))
                .build();
    }


}
