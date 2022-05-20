package digital.windmill.audra.graphql.facade.impl;

import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.graphql.facade.PolicyFacade;
import digital.windmill.audra.graphql.mapper.PolicyMapper;
import digital.windmill.audra.graphql.type.DeletedNodes;
import digital.windmill.audra.graphql.type.Policy;
import digital.windmill.audra.graphql.type.input.CreatePolicyInput;
import digital.windmill.audra.graphql.type.input.DeletePoliciesInput;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.PolicyService;
import digital.windmill.audra.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PolicyFacadeImpl implements PolicyFacade {

    private EmployeeService employeeService;
    private ResourceService resourceService;
    private PolicyMapper policyMapper;
    private PolicyService policyService;

    @Override
    public Policy createPolicy(CreatePolicyInput input, UUID ownerUuid) {
        var owner = employeeService.findEmployeeByUuid(ownerUuid);
        var file = resourceService.findResourceByUuid(input.getFile().getId()).orElse(null);
        var policyToBeSaved = policyMapper.mapCreatePolicyInputToPolicyEntity(owner, file, input);
        var savedPolicy = policyService.save(policyToBeSaved);
        return policyMapper.mapPolicyEntityToPolicy(savedPolicy);
    }

    @Override
    public DeletedNodes deletePolicies(DeletePoliciesInput input) {
        var policiesToBeDeleted = input.getPolicies().getIds()
                .stream()
                .map(p -> policyService.findPolicyByUuid(p))
                .toList();

        var deletedPolicies = policiesToBeDeleted.stream()
                .map(policyService::deletePolicy)
                .toList();

        return DeletedNodes.builder()
                .ids(deletedPolicies.stream().map(PolicyEntity::getUuid).toList())
                .build();
    }

    @Override
    public Page<Policy> getPolicies(PoliciesInput input) {
        return policyService.getPolicies(input)
                .map(policyMapper::mapPolicyEntityToPolicy);

    }
}
