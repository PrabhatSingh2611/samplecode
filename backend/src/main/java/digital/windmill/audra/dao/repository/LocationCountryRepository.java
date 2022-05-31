package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.LocationCountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationCountryRepository extends JpaRepository<LocationCountryEntity, Long>, JpaSpecificationExecutor<LocationCountryEntity> {

    @Query("SELECT c FROM LocationCountryEntity c WHERE c.uuid = :uuid")
    Optional<LocationCountryEntity> findByUuid(@Param("uuid") UUID uuid);
}
