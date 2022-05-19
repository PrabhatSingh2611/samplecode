package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long>, JpaSpecificationExecutor<SurveyEntity> {

    @Query("SELECT s FROM SurveyEntity s WHERE s.uuid = :uuid")
    Optional<SurveyEntity> findSurveyByUuid(@Param("uuid") UUID uuid);

}
