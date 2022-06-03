package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.AssetTypeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetTypeCategoryRepository extends JpaRepository<AssetTypeCategoryEntity, Long>, JpaSpecificationExecutor<AssetTypeCategoryEntity> {

    @Query("SELECT c FROM AssetTypeCategoryEntity c WHERE c.uuid = :uuid")
    Optional<AssetTypeCategoryEntity> findByUuid(@Param("uuid") UUID uuid);

}