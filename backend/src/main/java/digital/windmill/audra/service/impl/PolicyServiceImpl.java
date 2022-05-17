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

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private PolicyRepository policyRepository;

    @Override
    public PolicyEntity createPolicy(PolicyEntity policyToBeSaved) {
        return policyRepository.save(policyToBeSaved);
    }

    @Override
    public PolicyEntity findPolicyByUuid(UUID uuid) {
        return policyRepository.findPolicyByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Policy not available for given UUID : " + uuid.toString())
        );
    }

    @Override
    public PolicyEntity deletePolicy(PolicyEntity entity) {
        policyRepository.delete(entity);
        return entity;
    }

    @Override
    public Page<PolicyEntity> getPolicies(PoliciesInput input) {
        var specification = PolicySpecification.byPoliciesInput(input);

        var itemsPerPage = Optional.of(input).map(PoliciesInput::getPagination).
                map(PageInput::getItemsPerPage).orElse(DEFAULT_PAGE_SIZE);

        var pageNumber = Optional.of(input).map(PoliciesInput::getPagination).
                map(PageInput::getPageNumber).orElse(DEFAULT_PAGE_NUMBER);

        var pageable = PageRequest.of(pageNumber, itemsPerPage);
        return policyRepository.findAll(specification, pageable);
    }
}
