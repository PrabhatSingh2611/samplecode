package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.PlaybookStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PlaybookStepRepository extends JpaRepository<PlaybookStepEntity, Long>, JpaSpecificationExecutor<PlaybookStepEntity> {

}
