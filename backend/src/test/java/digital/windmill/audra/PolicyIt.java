package digital.windmill.audra;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import digital.windmill.audra.graphql.type.input.PolicyInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class PolicyIt extends AbstractIntegrationTest {

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldCreatePolicy() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/createPolicy.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/createPolicy.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldDeletePolicy() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/deletePolicies.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/deletePolicies.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllPoliciesByAllInputField() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/getAllPoliciesByInput.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAllPoliciesByInput.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllPoliciesByAllInputFieldWhenQueryIsNull() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/getAllPoliciesByInputIsNull.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAllPoliciesByInputIsNull.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldGetPolicy() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new PolicyInput() {{
            setId(UUID.fromString("239e8741-f04d-406a-bf7d-e2feaf8f5619"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/getPolicy.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        assertEquals("239e8741-f04d-406a-bf7d-e2feaf8f5619", response.get("$.data.policy.policy.id"));
        assertEquals("policy_title_1", response.get("$.data.policy.policy.title"));
        assertEquals("b5673d40-ac1f-4092-8361-80bcdc182a07", response.get("$.data.policy.policy.file.id"));
        assertEquals("5bea1a4d-b457-47c9-bf8b-72f50182b707", response.get("$.data.policy.policy.owner.id"));
        assertEquals("2022-02-02T13:25:34.480Z", response.get("$.data.policy.policy.publicationDate"));
        assertEquals("PUBLISHED", response.get("$.data.policy.policy.status"));
    }
}
