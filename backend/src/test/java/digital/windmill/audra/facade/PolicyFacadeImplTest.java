package digital.windmill.audra.facade;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.facade.impl.PolicyFacadeImpl;
import digital.windmill.audra.graphql.mapper.PolicyMapper;
import digital.windmill.audra.graphql.type.Policy;
import digital.windmill.audra.graphql.type.input.CreatePolicyInput;
import digital.windmill.audra.graphql.type.input.DeletePoliciesInput;
import digital.windmill.audra.graphql.type.input.NodesInput;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import digital.windmill.audra.graphql.type.input.ResourceInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.PolicyService;
import digital.windmill.audra.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolicyFacadeImplTest {

    private static final UUID RESOURCE_UUID = UUID.randomUUID();
    private static final UUID OWNER_UUID = UUID.randomUUID();
    private static final UUID POLICY_UUID = UUID.randomUUID();
    @InjectMocks
    PolicyFacadeImpl facade;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private ResourceService resourceService;
    @Mock
    private PolicyMapper policyMapper;
    @Mock
    private PolicyService policyService;

    @Test
    void shouldCreatePolicy(@Mock CreatePolicyInput input,
                            @Mock EmployeeEntity employeeEntity,
                            @Mock ResourceEntity file,
                            @Mock PolicyEntity policyEntity,
                            @Mock Policy policy,
                            @Mock ResourceInput resourceInput) {
        when(employeeService.findEmployeeByUuid(any(UUID.class)))
                .thenReturn(employeeEntity);
        when(resourceService.findResourceByUuid(any(UUID.class)))
                .thenReturn(Optional.of(file));
        when(input.getFile()).thenReturn(resourceInput);
        when(input.getFile().getId()).thenReturn(RESOURCE_UUID);
        when(policyMapper.mapCreatePolicyInputToPolicyEntity(any(EmployeeEntity.class),
                any(ResourceEntity.class),
                any(CreatePolicyInput.class)))
                .thenReturn(policyEntity);
        when(policyService.save(any(PolicyEntity.class))).thenReturn(policyEntity);
        when(policyMapper.mapPolicyEntityToPolicy(any(PolicyEntity.class)))
                .thenReturn(policy);

        var result = facade.createPolicy(input, OWNER_UUID);
        assertNotNull(result);
        assertSame(policy, result);
    }

    @Test
    void shouldDeletePolicy(@Mock DeletePoliciesInput input,
                            @Mock PolicyEntity policyEntity,
                            @Mock NodesInput nodesInput) {
        when(policyService.findPolicyByUuid(any(UUID.class))).thenReturn(policyEntity);
        when(policyService.deletePolicy(any(PolicyEntity.class))).thenReturn(policyEntity);
        when(input.getPolicies()).thenReturn(nodesInput);
        when(input.getPolicies().getIds()).thenReturn(List.of(POLICY_UUID));

        var result = facade.deletePolicies(input);
        assertNotNull(result);
        assertSame(policyEntity.getUuid(), result.getIds().get(0));

    }

    @Test
    void shouldGetPolicy(@Mock PoliciesInput input,
                         @Mock PolicyEntity policyEntity,
                         @Mock Policy policy) {

        var page = createOneItemPage(policyEntity);

        when(policyService.getPolicies(any(PoliciesInput.class))).thenReturn(page);
        when(policyMapper.mapPolicyEntityToPolicy(any(PolicyEntity.class)))
                .thenReturn(policy);

        var result = facade.getPolicies(input);
        assertNotNull(result);
        assertSame(page.getContent().get(0).getUuid(), result.getContent().get(0).getId());
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}