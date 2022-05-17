package digital.windmill.audra.graphql.facade;

import digital.windmill.audra.graphql.type.DeletedNodes;
import digital.windmill.audra.graphql.type.Policy;
import digital.windmill.audra.graphql.type.input.CreatePolicyInput;
import digital.windmill.audra.graphql.type.input.DeletePoliciesInput;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PolicyFacade {
    /**
     * @param input     information used for creating new Policy
     * @param ownerUuid temporary it will be replaced in future after adding Auth to the app
     * @return created Policy
     */
    Policy createPolicy(CreatePolicyInput input, UUID ownerUuid);

    /**
     * @param input takes multiple UUID to delete policy
     * @return multiple deleted policy's UUID
     */
    DeletedNodes deletePolicies(DeletePoliciesInput input);

    /**
     * @param input input for query result
     * @return a list of Policy
     */
    Page<Policy> getPolicies(PoliciesInput input);
}
