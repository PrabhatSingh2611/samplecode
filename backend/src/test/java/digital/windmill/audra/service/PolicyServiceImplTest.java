package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.dao.repository.PolicyRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import digital.windmill.audra.service.impl.PolicyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolicyServiceImplTest {

    private static final UUID POLICY_UUID = UUID.randomUUID();
    @InjectMocks
    PolicyServiceImpl service;
    @Mock
    private PolicyRepository policyRepository;

    @Test
    void shouldCreatePolicy(@Mock PolicyEntity policyToBeSaved) {
        when(policyRepository.save(any(PolicyEntity.class)))
                .thenReturn(policyToBeSaved);

        var result = service.createPolicy(policyToBeSaved);
        assertNotNull(result);
        assertSame(policyToBeSaved, result);
    }

    @Test
    void shouldFindPolicyByUuid(@Mock PolicyEntity policyEntity) {
        when(policyRepository.findPolicyByUuid(any(UUID.class))).thenReturn(Optional.of(policyEntity));
        var result = service.findPolicyByUuid(POLICY_UUID);
        assertNotNull(result);
        assertSame(policyEntity, result);
    }

    @Test
    void shouldThrowDataNotFoundWhenPolicyInputIsNull() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findPolicyByUuid(POLICY_UUID));
    }

    @Test
    void shouldDeletePolicy(@Mock PolicyEntity entity) {
        var result = service.deletePolicy(entity);
        assertNotNull(result);
        assertSame(entity, result);
    }

    @Test
    void shouldFindPolicyByInputField(@Mock PoliciesInput input,
                                      @Mock PolicyEntity policyEntity) {

        var page = createOneItemPage(policyEntity);
        when(policyRepository.findAll((Specification<PolicyEntity>) any(), any(PageRequest.class)))
                .thenReturn(page);

        var result = service.getPolicies(input);
        assertNotNull(result);
        assertSame(page, result);
    }

    private <T> Page<T> createOneItemPage(T item) {
        return new PageImpl<>(List.of(item));
    }
}