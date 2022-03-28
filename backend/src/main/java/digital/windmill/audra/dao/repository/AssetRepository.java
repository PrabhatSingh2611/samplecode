package digital.windmill.audra.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import digital.windmill.audra.dao.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssetRepository extends JpaRepository<AssetEntity, Long>, JpaSpecificationExecutor<AssetEntity> {

}
