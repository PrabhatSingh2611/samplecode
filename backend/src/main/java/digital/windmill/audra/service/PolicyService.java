package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PolicyService {

    PolicyEntity save(PolicyEntity policyEntity);

    PolicyEntity findPolicyByUuid(UUID uuid);

    PolicyEntity deletePolicy(PolicyEntity policyEntity);

    Page<PolicyEntity> getPolicies(PoliciesInput input);
}
