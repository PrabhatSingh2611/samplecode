package digital.windmill.audra.graphql.resolver.policy;

import digital.windmill.audra.graphql.facade.PolicyFacade;
import digital.windmill.audra.graphql.type.DeletePoliciesPayload;
import digital.windmill.audra.graphql.type.PolicyPayload;
import digital.windmill.audra.graphql.type.input.CreatePolicyInput;
import digital.windmill.audra.graphql.type.input.DeletePoliciesInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class PolicyMutationResolver implements GraphQLMutationResolver {

    private PolicyFacade policyFacade;

    public PolicyPayload createPolicy(CreatePolicyInput input) {
        //TODO: [VA-214] change to the UUID of the current user after adding AUTH to the app
        var ownerUuid = UUID.fromString("48b560ab-7450-4088-b6bb-f57638ea0877");
        return PolicyPayload.builder()
                .policy(policyFacade.createPolicy(input, ownerUuid))
                .build();
    }

    public DeletePoliciesPayload deletePolicies(DeletePoliciesInput input) {
        return DeletePoliciesPayload.builder()
                .policies(policyFacade.deletePolicies(input))
                .build();
    }
}