package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import digital.windmill.audra.graphql.type.input.*;
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
    var where = EmployeesInput.builder().where(
                    EmployeeWhereInput.builder()
                            .build())
            .build();
    var input = objectMapper.createObjectNode().putPOJO("input", where);
    GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getEmployees.graphql", input);

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

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldUpdateEmployee() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/updateEmployee.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/updateEmployee.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnEmployeesFiltered() throws IOException, URISyntaxException {
        var where = EmployeesInput.builder().where(
                        EmployeeWhereInput.builder()
                                .query("Jacob")
                                .location(NodeInput.of("b7f46256-e21d-483b-be29-8bf7617bc3c3"))
                                .build())
                .build();
        var input = objectMapper.createObjectNode().putPOJO("input", where);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getEmployees.graphql", input);
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getEmployeesFiltered.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnEmployeesPaginated() throws IOException, URISyntaxException {
        var where = EmployeesInput.builder().pagination(
                        PageInput.builder()
                                .pageNumber(0)
                                .itemsPerPage(5)
                                .build())
                .build();
        var input = objectMapper.createObjectNode().putPOJO("input", where);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getEmployees.graphql", input);

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getEmployeesPaginated.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }
}
