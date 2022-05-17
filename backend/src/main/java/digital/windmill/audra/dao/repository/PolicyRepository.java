package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PolicyRepository extends JpaRepository<PolicyEntity, Long>,
        JpaSpecificationExecutor<PolicyEntity> {
    @Query("select p from PolicyEntity p where p.uuid = :uuid")
    Optional<PolicyEntity> findPolicyByUuid(@Param("uuid") UUID uuid);
}
