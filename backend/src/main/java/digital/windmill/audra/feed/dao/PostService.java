package digital.windmill.audra.feed.dao;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.feed.dao.entity.PostEntity;
import digital.windmill.audra.feed.dao.repository.PostRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Component
public class PostService {

    private final PostRepository repository;

    public PostEntity createPost(String text, EmployeeEntity author) {
        PostEntity post = new PostEntity();
        post.setUuid(UUID.randomUUID());
        post.setText(text);
        post.setAuthor(author);
        return repository.save(post);
    }

}
