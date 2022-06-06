package digital.windmill.audra.feed.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import digital.windmill.audra.feed.dao.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
