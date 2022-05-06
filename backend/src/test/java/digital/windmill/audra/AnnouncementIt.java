package digital.windmill.audra;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
class AnnouncementIt extends AbstractIntegrationTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAnnouncement() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/getAnnouncement.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAnnouncement.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllAnnouncements() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/getAnnouncements.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAnnouncements.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    void shouldCreateAnnouncement() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/createAnnouncement.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/createAnnouncement.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldUpdateAnnouncement() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/updateAnnouncement.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/updateAnnouncement.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldDeleteAnnouncement() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/deleteAnnouncement.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/deleteAnnouncement.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }
}
