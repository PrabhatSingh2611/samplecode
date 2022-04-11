package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeePositionRepository extends
        JpaRepository<EmployeePositionEntity, Long>, JpaSpecificationExecutor<EmployeePositionEntity> {

    @Query("SELECT a FROM EmployeePositionEntity a WHERE a.uuid = :uuid")
    Optional<EmployeePositionEntity> findByUuid(@Param("uuid") UUID uuid);

    Optional<EmployeePositionEntity> findByName(String name);
}
