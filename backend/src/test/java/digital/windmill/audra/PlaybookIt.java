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
class PlaybookIt extends AbstractIntegrationTest {


    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnPlaybook() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/playbook/getPlaybook.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/playbook/getPlaybook.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllPlaybooks() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/playbook/getPlaybooks.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/playbook/getPlaybooks.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }
}
