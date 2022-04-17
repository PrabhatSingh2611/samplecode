package digital.windmill.audra.graphql.resolver.employee;

import digital.windmill.audra.graphql.facade.EmployeeFacade;
import digital.windmill.audra.graphql.type.EmployeePayload;
import digital.windmill.audra.graphql.type.input.CreateEmployeeInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMutationResolver implements GraphQLMutationResolver {

    private EmployeeFacade employeeFacade;

    public EmployeePayload createEmployee(CreateEmployeeInput input){
       return EmployeePayload
               .builder()
               .item(employeeFacade.createEmployee(input))
               .build();
    }
}
