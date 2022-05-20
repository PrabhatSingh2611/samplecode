package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PolicyService {

    /**
     * This method will save a Policy by a specific value.
     *
     * @param policyEntity policyEntity of which policy we should save
     * @return created PolicyEntity
     */
    PolicyEntity save(PolicyEntity policyEntity);

    /**
     * This method will find a Policy by a specific id
     *
     * @param uuid it is value used to be search
     * @return a specific PolicyEntity
     */
    PolicyEntity findPolicyByUuid(UUID uuid);

    /**
     * This method will delete a Policy by a specific value
     *
     * @param policyEntity Policy of which we should be deleting
     * @return deleted policyEntity
     */
    PolicyEntity deletePolicy(PolicyEntity policyEntity);

    /**
     * This method will return a list of policies
     *
     * @param input for query result
     * @return a list of policies including pagination
     */
    Page<PolicyEntity> getPolicies(PoliciesInput input);
}
