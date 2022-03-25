package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import digital.windmill.audra.graphql.type.input.AssetInput;
import digital.windmill.audra.graphql.type.input.AssetWhereInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.PageInput;
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
class AssetIt {

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

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllAssets() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/getAssets.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAssets.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAssetsFiltered() throws IOException, URISyntaxException {
        var where = AssetInput.builder().where(
                AssetWhereInput.builder()
                        .archived(false)
                        .type(NodeInput.of("5478b586-e607-4448-ac05-3e5f2adbbc1b"))
                        .employee(NodeInput.of("6bac1755-c88c-4462-ae14-527b54b03e0d"))
                        .build())
                .build();
        var input = objectMapper.createObjectNode().putPOJO("input", where);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getAssets.graphql", input);

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAssetsFiltered.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAssetsPaginated() throws IOException, URISyntaxException {
        var where = AssetInput.builder().page(
                PageInput.builder()
                        .pageNumber(2)
                        .itemsPerPage(3)
                        .build())
                .build();
        var input = objectMapper.createObjectNode().putPOJO("input", where);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getAssets.graphql", input);

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAssetsPaginated.json");
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
