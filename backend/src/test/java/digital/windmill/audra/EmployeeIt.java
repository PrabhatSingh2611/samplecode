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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql("classpath:/db/delete-all.sql")
public class EmployeeIt {
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("rawtypes")
    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(DockerImageName.parse(PostgreSQLContainer.IMAGE)).withPassword("postgres").withUsername("postgres");

    @DynamicPropertySource
    static void dataSourceProperes(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.liquibase.url", postgreSQLContainer::getJdbcUrl);
    }

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
