package digital.windmill.audra.feed;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;

import digital.windmill.audra.AbstractIntegrationTest;
import digital.windmill.audra.feed.graphql.type.CreatePostInput;
import digital.windmill.audra.feed.graphql.type.Post;
import digital.windmill.audra.graphql.type.Resource;
import digital.windmill.audra.graphql.type.input.NodesInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class PostIt extends AbstractIntegrationTest {

    @Test
    @Sql("classpath:/db/feed/insert-initial-entities.sql")
    void shouldNotCreateEmptyPost() throws IOException, URISyntaxException {
        CreatePostInput feedInput = CreatePostInput.builder()
                .build();
        ObjectNode input = JsonNodeFactory.instance.objectNode();
        input.putPOJO("input", feedInput);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/feed/createPostRequestTemplate.graphql", input);
        log.info(response.readTree().toPrettyString());
        assertEquals("Exception while fetching data (/createPost) : Post doesn't allow empty text and no images at the same time.", 
                response.get("$.errors[0].message", String.class));
    }

    @Test
    @Sql("classpath:/db/feed/insert-initial-entities.sql")
    void shouldCreatePost() throws IOException, URISyntaxException {
        CreatePostInput feedInput = CreatePostInput.builder()
                .text("New post message")
                .images(NodesInput.of("024ed048-68e9-47ca-99b8-3ee81dc70245", "2e9a7827-5abb-4ff5-a32c-0d5fe49bc3c5", "b5673d40-ac1f-4092-8361-80bcdc182a07"))
                .build();
        ObjectNode input = JsonNodeFactory.instance.objectNode();
        input.putPOJO("input", feedInput);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/feed/createPostRequestTemplate.graphql", input);
        log.info(response.readTree().toPrettyString());
        Post actual = response.get("$.data.createPost.post", Post.class);
        log.info(actual.toString());
        assertNotNull(actual.getId());
        assertNotNull(actual.getAuthor());
        assertEquals("New post message", actual.getText());
        assertEquals(UUID.fromString("493a5be9-01ba-47c6-95c1-29c230528520"), actual.getAuthor().getId());
        assertTrue(CollectionUtils.isEqualCollection(List.of(UUID.fromString("b5673d40-ac1f-4092-8361-80bcdc182a07"), UUID.fromString("024ed048-68e9-47ca-99b8-3ee81dc70245")), 
                actual.getImages().stream().map(Resource::getId).toList()));
    }
}
