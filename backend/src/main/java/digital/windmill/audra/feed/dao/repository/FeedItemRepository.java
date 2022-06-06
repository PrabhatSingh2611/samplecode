package digital.windmill.audra.feed.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import digital.windmill.audra.feed.dao.entity.FeedItemEntity;

public interface FeedItemRepository extends JpaRepository<FeedItemEntity, Long>, JpaSpecificationExecutor<FeedItemEntity> {

}
