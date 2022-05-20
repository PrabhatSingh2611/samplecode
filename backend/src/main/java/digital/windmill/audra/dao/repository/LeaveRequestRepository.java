package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, Long>, JpaSpecificationExecutor<LeaveRequestEntity> {

    @Query("SELECT lr FROM LeaveRequestEntity lr WHERE lr.uuid = :uuid")
    Optional<LeaveRequestEntity> findLeaveRequestByUuid(@Param("uuid") UUID uuid);

}
