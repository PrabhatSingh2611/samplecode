package digital.windmill.audra.graphql.resolver.policy;

import digital.windmill.audra.graphql.facade.PolicyFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Policy;
import digital.windmill.audra.graphql.type.PolicyPayload;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import digital.windmill.audra.graphql.type.input.PolicyInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PolicyResolver implements GraphQLQueryResolver {

    private PolicyFacade policyFacade;

    public ConnectionPayload<Policy> policies(PoliciesInput input) {
        return ConnectionUtils.buildPayload(policyFacade.getPolicies(input));
    }

    public PolicyPayload policy(@NonNull PolicyInput input) {
        return PolicyPayload.builder()
                .policy(policyFacade.getPolicy(input))
                .build();
    }
}
