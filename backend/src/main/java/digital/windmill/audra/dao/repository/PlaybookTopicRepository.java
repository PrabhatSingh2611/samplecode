package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionTopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PlaybookTopicRepository extends JpaRepository<PlaybookSectionTopicEntity, Long>, JpaSpecificationExecutor<PlaybookSectionTopicEntity> {

    @Query("SELECT p FROM PlaybookSectionTopicEntity p WHERE p.uuid = :uuid")
    Optional<PlaybookSectionTopicEntity> findTopicByUuid(@Param("uuid") UUID uuid);

    @Query("SELECT MAX(s.sort) FROM PlaybookSectionTopicEntity s WHERE s.section = :section")
    Optional<Integer> getLastPostPosition(@Param("section") PlaybookSectionEntity playbookSectionEntity);

    @Modifying
    @Query(value = "UPDATE PlaybookSectionTopicEntity t SET t.sort = t.sort + 1 WHERE t.section = :section AND t.sort > :sort")
    void shiftSort(@Param("section") PlaybookSectionEntity section, @Param("sort") Integer sort);
}
