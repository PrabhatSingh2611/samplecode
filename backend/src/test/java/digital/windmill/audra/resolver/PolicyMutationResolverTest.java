package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.PolicyFacade;
import digital.windmill.audra.graphql.resolver.policy.PolicyMutationResolver;
import digital.windmill.audra.graphql.type.DeletedNodes;
import digital.windmill.audra.graphql.type.Policy;
import digital.windmill.audra.graphql.type.input.CreatePolicyInput;
import digital.windmill.audra.graphql.type.input.DeletePoliciesInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolicyMutationResolverTest {

    @InjectMocks
    PolicyMutationResolver resolver;
    @Mock
    private PolicyFacade policyFacade;

    @Test
    void shouldCreatePolicy(@Mock CreatePolicyInput input,
                            @Mock Policy policy) {
        when(policyFacade.createPolicy(any(CreatePolicyInput.class), any(UUID.class)))
                .thenReturn(policy);

        var result = resolver.createPolicy(input);
        assertNotNull(result);
        assertSame(policy, result.getPolicy());
    }

    @Test
    void shouldCreatePolicy(@Mock DeletePoliciesInput input,
                            @Mock DeletedNodes deletedNodes) {

        when(policyFacade.deletePolicies(any(DeletePoliciesInput.class)))
                .thenReturn(deletedNodes);

        var result = resolver.deletePolicies(input);
        assertNotNull(result);
        assertSame(deletedNodes, result.getPolicies());
    }
}