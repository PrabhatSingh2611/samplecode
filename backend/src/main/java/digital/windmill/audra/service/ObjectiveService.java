package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.graphql.type.input.ObjectivesInput;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ObjectiveService {

    ObjectiveEntity save(ObjectiveEntity objectiveEntity);

    ObjectiveEntity findObjectiveByUuid(UUID uuid);

    ObjectiveEntity deleteObjective(ObjectiveEntity objectiveEntity);

    Page<ObjectiveEntity> findAllObjectives(ObjectivesInput input);
}
