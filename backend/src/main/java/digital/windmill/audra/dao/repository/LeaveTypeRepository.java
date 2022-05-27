package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.LeaveTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveTypeEntity, Long>, JpaSpecificationExecutor<LeaveTypeEntity> {

    @Query("SELECT lt FROM LeaveTypeEntity lt WHERE lt.uuid = :uuid")
    Optional<LeaveTypeEntity> findByUuid(@Param("uuid") UUID uuid);
}
