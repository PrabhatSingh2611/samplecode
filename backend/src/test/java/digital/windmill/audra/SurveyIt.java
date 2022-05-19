package digital.windmill.audra;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
class SurveyIt extends AbstractIntegrationTest {


    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnSurvey() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/getSurvey.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getSurvey.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllSurveys() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/getSurveys.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getSurveys.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    void shouldCreateSurvey() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/createSurvey.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/createSurvey.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldPatchSurvey() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/patchSurvey.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/patchSurvey.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldPatchSurveyWithoutQuestions() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/patchSurveyWithoutQuestions.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/patchSurveyWithoutQuestions.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldPatchSurveyWithoutOptions() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/patchSurveyWithoutOptions.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/patchSurveyWithoutOptions.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldDeleteSurvey() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/deleteSurvey.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/deleteSurvey.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }
}
