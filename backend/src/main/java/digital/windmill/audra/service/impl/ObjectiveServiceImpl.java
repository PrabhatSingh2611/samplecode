package digital.windmill.audra.service.impl;

import digital.windmill.audra.dao.ObjectiveSpecification;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.repository.ObjectiveRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import digital.windmill.audra.service.ObjectiveService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ObjectiveServiceImpl implements ObjectiveService {

    private static final Integer DEFAULT_PAGE_NUMBER=0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private ObjectiveRepository objectiveRepository;

    @Override
    public ObjectiveEntity findObjectiveByUuid(UUID uuid) {
        return objectiveRepository.findObjectiveByUuid(uuid).orElseThrow(
                () -> new DataNotFoundException("Objective not available for given UUID : " + uuid.toString())
        );
    }

    public Page<ObjectiveEntity> findAllObjectives(ObjectivesInput input) {
        Specification<ObjectiveEntity> specification = ObjectiveSpecification.allObjectives();
        PageRequest pagination = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        return objectiveRepository.findAll(specification, pagination);
    }

    @Override
    public ObjectiveEntity createObjective(ObjectiveEntity objectiveEntity) {
        return objectiveRepository.save(objectiveEntity);
    }

    @Override
    public ObjectiveEntity updateObjective(ObjectiveEntity updatedObjectiveEntity) {
        return objectiveRepository.save(updatedObjectiveEntity);
    }

    @Override
    public ObjectiveEntity deleteObjective(ObjectiveEntity objectiveToBeDeleted) {
        objectiveRepository.delete(objectiveToBeDeleted);
        return objectiveToBeDeleted;
    }
}
