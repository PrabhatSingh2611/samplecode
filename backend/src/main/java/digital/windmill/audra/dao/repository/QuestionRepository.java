package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;


public interface QuestionRepository extends JpaRepository<QuestionEntity, Long>, JpaSpecificationExecutor<QuestionEntity> {

    @Query("SELECT q FROM QuestionEntity q WHERE q.uuid = :uuid")
    Optional<QuestionEntity> findQuestionByUuid(@Param("uuid") UUID uuid);
}
