package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.PlaybookEntity;
import digital.windmill.audra.dao.entity.PlaybookSectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaybookSectionRepository extends JpaRepository<PlaybookSectionEntity, Long>, JpaSpecificationExecutor<PlaybookSectionEntity> {
    Optional<PlaybookSectionEntity> findOneByUuid(UUID uuid);

    @Query("SELECT MAX(s.sort) FROM PlaybookSectionEntity s WHERE s.playbook = :playbook")
    Optional<Integer> getLastSortPosition(@Param("playbook") PlaybookEntity playbookEntity);

    @Modifying
    @Query(value = "UPDATE PlaybookSectionEntity s SET s.sort = s.sort + 1 WHERE s.playbook = :playbook AND s.sort > :sort")
    void shiftSort(@Param("playbook") PlaybookEntity playbookEntity, @Param("sort") Integer sort);
}
