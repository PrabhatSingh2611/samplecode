package digital.windmill.audra;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql("classpath:/db/delete-all.sql")
public class VacancyIt {
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

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
    void shouldUpdateVacancy() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/updateVacancy.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = Files.readString(Paths.get(VacancyIt.class.getClassLoader()
                .getResource("graphql/response/updateVacancy.json")
                .toURI()), Charset.forName("UTF-8"));
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

}
