package digital.windmill.audra.feed.graphql.mapper;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import digital.windmill.audra.feed.dao.entity.FeedItemEntity;
import digital.windmill.audra.feed.dao.entity.PostEntity;
import digital.windmill.audra.feed.graphql.type.Post;

@ExtendWith(MockitoExtension.class)
class FeedItemMapperTest {

    @Mock 
    private PostMapper postMapper;

    @InjectMocks
    private FeedItemMapperImpl mapper = new FeedItemMapperImpl();

    @Test
    void shouldMapPost(@Mock PostEntity entity,
            @Mock Post post) {
        when(postMapper.map(entity)).thenReturn(post);
        Assertions.assertSame(post, mapper.map(entity));
    }

    @Test
    void shouldMapNullValue() {
        Assertions.assertNull(mapper.map(null));
    }

    @Test
    void shouldNotMapNewImplementation() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> mapper.map(new FeedItemEntity()));
    }

}
