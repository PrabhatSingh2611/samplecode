package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PlaybookRepository extends JpaRepository<PlaybookEntity, Long>, JpaSpecificationExecutor<PlaybookEntity> {

    @Query("SELECT p FROM PlaybookEntity p WHERE p.uuid = :uuid")
    Optional<PlaybookEntity> findPlaybookByUuid(@Param("uuid") UUID uuid);
}
