package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.AssetTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetTypeRepository extends JpaRepository<AssetTypeEntity, Long>, JpaSpecificationExecutor<AssetTypeEntity> {

    @Query("SELECT a FROM AssetTypeEntity a WHERE a.uuid = :uuid")
    Optional<AssetTypeEntity> findByUuid(@Param("uuid") UUID uuid);
    
}