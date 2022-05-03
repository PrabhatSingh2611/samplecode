package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, Long>, JpaSpecificationExecutor<AssetEntity> {

    @Query("SELECT a FROM AssetEntity a WHERE a.uuid = :uuid")
    Optional<AssetEntity> findByUuid(@Param("uuid") UUID uuid);

}
