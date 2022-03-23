package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.jdbc.SqlMergeMode.MergeMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
@SqlMergeMode(MergeMode.MERGE)
@Sql("classpath:/db/delete-all.sql")
class AudraIt {

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
    void shouldReturnAssetById() throws IOException, URISyntaxException {
        ObjectNode variables = objectMapper.createObjectNode();
        variables.put("id", 1);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getAsset.graphql", variables);
        
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAsset.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    private String readFromResource(String path) throws IOException, URISyntaxException {
        return Files.readString(Paths.get(resourceUri(path)), StandardCharsets.UTF_8);
    }

    private URI resourceUri(String path) throws URISyntaxException {
        return AudraIt
                .class
                .getClassLoader()
                .getResource(path)
                .toURI();
    }

}
