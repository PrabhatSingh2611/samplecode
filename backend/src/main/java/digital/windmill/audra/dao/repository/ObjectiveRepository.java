package digital.windmill.audra.dao.repository;


import digital.windmill.audra.dao.entity.ObjectiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ObjectiveRepository extends
        JpaRepository<ObjectiveEntity, Long>, JpaSpecificationExecutor<ObjectiveEntity> {

    @Query("SELECT a FROM ObjectiveEntity a WHERE a.uuid = :uuid")
    Optional<ObjectiveEntity> findByUuid(@Param("uuid") UUID uuid);
}
