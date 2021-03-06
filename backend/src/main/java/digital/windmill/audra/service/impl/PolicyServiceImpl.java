package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.PolicySpecification;
import digital.windmill.audra.dao.entity.PolicyEntity;
import digital.windmill.audra.dao.repository.PolicyRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.PageInput;
import digital.windmill.audra.graphql.type.input.PoliciesInput;
import digital.windmill.audra.service.PolicyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private PolicyRepository policyRepository;

    @Override
    public PolicyEntity save(PolicyEntity policyEntity) {
        return policyRepository.save(policyEntity);
    }

    @Override
    public PolicyEntity findPolicyByUuid(UUID uuid) {
        return policyRepository.findPolicyByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Policy not available for given UUID : " + uuid.toString())
        );
    }

    @Override
    public PolicyEntity deletePolicy(PolicyEntity policyEntity) {
        policyRepository.delete(policyEntity);
        return policyEntity;
    }

    @Override
    public Page<PolicyEntity> getPolicies(PoliciesInput input) {
        var specification = PolicySpecification.byPoliciesInput(input.getWhere(), input.getSort());

        var itemsPerPage = Optional.of(input).map(PoliciesInput::getPagination).
                map(PageInput::getItemsPerPage).orElse(PageInput.MAX_ITEMS_PER_PAGE);

        var pageNumber = Optional.of(input).map(PoliciesInput::getPagination).
                map(PageInput::getPageNumber).orElse(0);

        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return policyRepository.findAll(specification, pageable);
    }
}
