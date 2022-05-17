package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PolicyService {
    /**
     * @param policyToBeSaved PolicyEntity that will be saved
     * @return saved Policy
     */
    PolicyEntity createPolicy(PolicyEntity policyToBeSaved);

    /**
     * @param uuid uuid by which it will search Policy
     * @return search Policy by given uuid
     */
    PolicyEntity findPolicyByUuid(UUID uuid);

    /**
     * @param entity PolicyEntity that will be saved
     * @return saved Policy
     */
    PolicyEntity deletePolicy(PolicyEntity entity);

    /**
     * @param input for query result
     * @return a list of PolicyEntity including pagination
     */
    Page<PolicyEntity> getPolicies(PoliciesInput input);
}
