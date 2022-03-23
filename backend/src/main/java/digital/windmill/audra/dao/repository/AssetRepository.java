package digital.windmill.audra.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import digital.windmill.audra.dao.entity.AssetEntity;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {

}
