package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.PlaybookTaskListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PlaybookTaskListRepository extends JpaRepository<PlaybookTaskListEntity, Long>, JpaSpecificationExecutor<PlaybookTaskListEntity> {

    @Query("SELECT ptl FROM PlaybookTaskListEntity ptl WHERE ptl.uuid = :uuid")
    Optional<PlaybookTaskListEntity> findPlaybookTaskListByUuid(@Param("uuid") UUID uuid);
}
