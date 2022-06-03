package digital.windmill.audra.resolver;

import digital.windmill.audra.graphql.facade.PolicyFacade;
import digital.windmill.audra.graphql.resolver.policy.PolicyResolver;
import digital.windmill.audra.graphql.type.Policy;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import digital.windmill.audra.graphql.type.input.PolicyInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolicyResolverTest {

    @InjectMocks
    PolicyResolver policyResolver;
    @Mock
    private PolicyFacade policyFacade;

    @Test
    void shouldGetPoliciesByInput(@Mock PoliciesInput input,
                                  @Mock Policy policy) {
        var page = createOneItemPage(policy);
        when(policyFacade.getPolicies(any(PoliciesInput.class))).thenReturn(page);

        var result = policyResolver.policies(input);
        assertNotNull(result);
        assertSame(policy, result.getItems().get(0));
    }

    @Test
    void shouldGetPolicy(@Mock PolicyInput input,
                         @Mock Policy policy) {
        when(policyFacade.getPolicy(input)).thenReturn(policy);
        var actualResult = policyResolver.policy(input);
        assertEquals(policy, actualResult.getPolicy());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}
