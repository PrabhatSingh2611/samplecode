package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphql.spring.boot.test.GraphQLResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VacancyIt  extends AbstractIntegrationTest {

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllVacanciesByAllInputFields() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/getVacanciesByAllInputFields.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = Files.readString(Paths.get(VacancyIt.class.getClassLoader()
                .getResource("graphql/response/getVacanciesByAllInputFields.json")
                .toURI()), Charset.forName("UTF-8"));
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllVacanciesByPositionAndStatus() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/getVacanciesByPositionAndStatus.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = Files.readString(Paths.get(VacancyIt.class.getClassLoader()
                .getResource("graphql/response/getVacanciesByPositionAndStatus.json")
                .toURI()), Charset.forName("UTF-8"));
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnVacancy() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/getVacancy.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = Files.readString(Paths.get(VacancyIt.class.getClassLoader()
                .getResource("graphql/response/getVacancy.json")
                .toURI()), Charset.forName("UTF-8"));
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldCreateVacancy() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/createVacancy.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = Files.readString(Paths.get(VacancyIt.class.getClassLoader()
                .getResource("graphql/response/createVacancy.json")
                .toURI()), Charset.forName("UTF-8"));
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldCreateVacancyNoAssignTo() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/createVacancyNoAssignTo.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = Files.readString(Paths.get(VacancyIt.class.getClassLoader()
                .getResource("graphql/response/createVacancyNoAssignTo.json")
                .toURI()), Charset.forName("UTF-8"));
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldUpdateVacancy() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/UpdateVacancy.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = Files.readString(Paths.get(VacancyIt.class.getClassLoader()
                .getResource("graphql/response/updateVacancy.json")
                .toURI()), Charset.forName("UTF-8"));
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldUpdateVacancyNoAssignTo() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/updateVacancyNoAssignTo.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = Files.readString(Paths.get(VacancyIt.class.getClassLoader()
                .getResource("graphql/response/updateVacancyNoAssignTo.json")
                .toURI()), Charset.forName("UTF-8"));
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }
}
