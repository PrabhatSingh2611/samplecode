package digital.windmill.audra.dao.repository;

import digital.windmill.audra.dao.entity.PlaybookTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PlaybookTaskRepository extends JpaRepository<PlaybookTaskEntity, Long>, JpaSpecificationExecutor<PlaybookTaskEntity> {

}
