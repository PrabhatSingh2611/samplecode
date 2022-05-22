package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long>, JpaSpecificationExecutor<CandidateEntity> {

    @Query("SELECT c FROM CandidateEntity c WHERE c.uuid = :uuid")
    Optional<CandidateEntity> findCandidateByUuid(@Param("uuid") UUID uuid);

}

