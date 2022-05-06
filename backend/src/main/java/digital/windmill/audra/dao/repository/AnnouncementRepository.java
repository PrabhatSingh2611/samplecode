package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long>
        , JpaSpecificationExecutor<AnnouncementEntity> {

    @Query("SELECT a FROM AnnouncementEntity a WHERE a.uuid = :uuid")
    Optional<AnnouncementEntity> findByUuid(@Param("uuid") UUID uuid);

}
