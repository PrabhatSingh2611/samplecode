package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EmployeePositionIt  extends AbstractIntegrationTest {
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldCreateEmployeePosition() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/createEmployeePosition.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/createEmployeePosition.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldUpdateEmployeePosition() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/updateEmployeePosition.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/updateEmployeePosition.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test 
    @Disabled("Doesn't work, because employee has link with position. Should be fixed!!!")
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldDeleteEmployeePosition() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/deleteEmployeePosition.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/deleteEmployeePosition.json");
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
