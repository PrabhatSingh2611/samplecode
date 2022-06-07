package digital.windmill.audra;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import digital.windmill.audra.graphql.type.enums.EmployeePositionsSort;
import digital.windmill.audra.graphql.type.input.EmployeePositionsInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class EmployeePositionIt extends AbstractIntegrationTest {

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


    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldGetEmployeePositionsWithDefaultInput() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new EmployeePositionsInput());

        var response = graphQLTestTemplate.perform("graphql/request/getEmployeePositions.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());


        assertEquals("2", response.get("$.data.employeePositions.totalItems"));
        assertEquals("1", response.get("$.data.employeePositions.pageInfo.currentPage"));
        assertEquals("1", response.get("$.data.employeePositions.pageInfo.totalPages"));
        assertEquals("cabfb51b-41c5-4f4d-afd5-146c796391ad", response.get("$.data.employeePositions.items[0].id"));
        assertEquals("79761728-8f51-475f-aa04-42385a0dfe35", response.get("$.data.employeePositions.items[1].id"));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldGetEmployeePositionsWithCustomInput() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new EmployeePositionsInput() {{
            setPagination(new PageInput(1, 1));
            setSort(List.of(EmployeePositionsSort.name_DESC));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/getEmployeePositions.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        assertEquals("2", response.get("$.data.employeePositions.totalItems"));
        assertEquals("1", response.get("$.data.employeePositions.pageInfo.currentPage"));
        assertEquals("2", response.get("$.data.employeePositions.pageInfo.totalPages"));
        assertEquals("79761728-8f51-475f-aa04-42385a0dfe35", response.get("$.data.employeePositions.items[0].id"));
    }

}
