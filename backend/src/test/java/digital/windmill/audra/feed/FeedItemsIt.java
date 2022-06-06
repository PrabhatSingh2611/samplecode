package digital.windmill.audra.feed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;

import digital.windmill.audra.AbstractIntegrationTest;
import digital.windmill.audra.feed.graphql.type.FeedInput;
import digital.windmill.audra.feed.graphql.type.FeedSort;
import digital.windmill.audra.graphql.type.FeedItem;
import digital.windmill.audra.utils.PageInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeedItemsIt extends AbstractIntegrationTest {

    @Test
    @Sql("classpath:/db/feed/insert-initial-entities.sql")
    void fetchFeedItemsWithPagination() throws IOException, URISyntaxException {
        FeedInput feedInput = FeedInput.builder()
                .pagination(PageInput.builder()
                        .pageNumber(2)
                        .itemsPerPage(2)
                        .build())
                .sort(List.of(FeedSort.createdAt_DESC))
                .build();
        ObjectNode input = JsonNodeFactory.instance.objectNode();
        input.putPOJO("input", feedInput);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/feed/getFeedItemsTemplate.graphql", input);
        log.info(response.readTree().toPrettyString());

        var expectedResponse = readFromResourceAsJsonNode("graphql/response/feed/getFeedItemsResponse.json");
        assertEquals(expectedResponse, response.get("$", JsonNode.class));
    }

    @Nested
    @Sql({"classpath:/db/delete-all.sql",
        "classpath:/db/feed/insert-initial-entities.sql"})
    class SortingTest {
        @Test
        void shouldReturnFeedItems_byCreatedAtDesc() throws IOException {
            FeedInput feedInput = FeedInput.builder()
                    .pagination(PageInput.builder()
                            .pageNumber(1)
                            .itemsPerPage(5)
                            .build())
                    .sort(List.of(FeedSort.createdAt_DESC))
                    .build();
            ObjectNode input = JsonNodeFactory.instance.objectNode();
            input.putPOJO("input", feedInput);
            GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/feed/getFeedItemsTemplate.graphql", input );
            log.info(response.readTree().toPrettyString());
            assertFeedItemsUuids(List.of(
                    UUID.fromString("facff19e-f6a8-4377-842f-81114b8a243d"),
                    UUID.fromString("58fedc11-c0f3-4ccb-81f8-12339fa6bc03"),
                    UUID.fromString("32e22341-040f-4b5f-9b1a-4dc2c59813f6"),
                    UUID.fromString("be0c9210-16d1-4ae8-918a-b3c951243c4c")), response);
        }

        @Test
        void shouldReturnFeedItems_byCreatedAtAsc() throws IOException {
            FeedInput feedInput = FeedInput.builder()
                    .pagination(PageInput.builder()
                            .pageNumber(1)
                            .itemsPerPage(5)
                            .build())
                    .sort(List.of(FeedSort.createdAt_ASC))
                    .build();
            ObjectNode input = JsonNodeFactory.instance.objectNode();
            input.putPOJO("input", feedInput);
            GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/feed/getFeedItemsTemplate.graphql", input );
            log.info(response.readTree().toPrettyString());
            assertFeedItemsUuids(List.of(
                    UUID.fromString("be0c9210-16d1-4ae8-918a-b3c951243c4c"),
                    UUID.fromString("32e22341-040f-4b5f-9b1a-4dc2c59813f6"),
                    UUID.fromString("58fedc11-c0f3-4ccb-81f8-12339fa6bc03"),
                    UUID.fromString("facff19e-f6a8-4377-842f-81114b8a243d")
                    ), response);
        }


        private void assertFeedItemsUuids(List<UUID> expectedUuids, GraphQLResponse response) {
            List<FeedItem> list = response.getList("$.data.feed.items", FeedItem.class);
            List<UUID> actualRequestUuids = list.stream().map(FeedItem::getId).toList();
            Assertions.assertEquals(expectedUuids, actualRequestUuids);
        }
    }
}
