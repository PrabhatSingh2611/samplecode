package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphql.spring.boot.test.GraphQLResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EmployeeIt extends AbstractIntegrationTest {

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllEmployees() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/getEmployees.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getEmployees.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldCreateEmployee() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/createEmployee.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/createEmployee.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

}
