package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

    @Query("FROM ResourceEntity r WHERE r.uuid = :uuid")
    Optional<ResourceEntity> findByUuid(@Param("uuid") UUID uuid);

}
