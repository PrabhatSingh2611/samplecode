package digital.windmill.audra.dao.repository;


import digital.windmill.audra.dao.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, JpaSpecificationExecutor<EmployeeEntity>
{

    @Query("SELECT a FROM EmployeeEntity a WHERE a.uuid = :uuid")
    Optional<EmployeeEntity> findByUuid(@Param("uuid") UUID uuid);


}
