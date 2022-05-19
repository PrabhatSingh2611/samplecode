package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OptionRepository extends JpaRepository<OptionEntity, Long>, JpaSpecificationExecutor<OptionEntity> {

}
