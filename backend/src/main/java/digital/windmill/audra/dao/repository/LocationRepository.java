package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long>, JpaSpecificationExecutor<LocationEntity> {

    @Query("SELECT l FROM LocationEntity l WHERE l.uuid = :uuid")
    Optional<LocationEntity> findByUuid(@Param("uuid") UUID uuid);
}
