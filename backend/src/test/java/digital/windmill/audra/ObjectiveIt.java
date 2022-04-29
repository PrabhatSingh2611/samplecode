package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ObjectiveIt extends AbstractIntegrationTest {
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnObjective() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/getObjective.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getObjective.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllObjectives() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/getObjectives.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getObjectives.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
     void shouldCreateObjective() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/createObjective.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/createObjective.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldUpdateObjective() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/updateObjective.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/updateObjective.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldDeleteObjective() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/deleteObjective.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/deleteObjective.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }
    private String readFromResource(String path) throws IOException, URISyntaxException {
        return Files.readString(Paths.get(resourceUri(path)), StandardCharsets.UTF_8);
    }

    private URI resourceUri(String path) throws URISyntaxException {
        return AssetIt
                .class
                .getClassLoader()
                .getResource(path)
                .toURI();
    }
}
