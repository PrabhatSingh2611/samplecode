package digital.windmill.audra.dao.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import digital.windmill.audra.dao.entity.ResourceEntity;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

    @Query("FROM ResourceEntity r WHERE r.uuid = :uuid")
    Optional<ResourceEntity> findByUuid(@Param("uuid") UUID uuid);

}
