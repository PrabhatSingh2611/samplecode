package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphql.spring.boot.test.GraphQLResponse;

import digital.windmill.audra.graphql.type.input.AssetWhereInput;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.PageInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class AssetIt extends AbstractIntegrationTest {

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAssetById() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/getAsset.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAsset.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllAssets() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/getAssets.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAssets.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAssetsFiltered() throws IOException, URISyntaxException {
        var where = AssetsInput.builder().where(
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
        var where = AssetsInput.builder().page(
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

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldCreateAsset() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/createAsset.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/createAsset.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldUpdateAsset() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/updateAsset.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/updateAsset.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

}
