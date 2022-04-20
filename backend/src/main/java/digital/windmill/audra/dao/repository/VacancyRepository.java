package digital.windmill.audra.dao.repository;


import digital.windmill.audra.dao.entity.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long>, JpaSpecificationExecutor<VacancyEntity> {

    @Query("SELECT a FROM VacancyEntity a WHERE a.uuid = :uuid")
    Optional<VacancyEntity> findVacancyByUuid(@Param("uuid") UUID uuid);




}
