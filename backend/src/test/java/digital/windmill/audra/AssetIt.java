package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import digital.windmill.audra.graphql.type.input.NodesInput;
import digital.windmill.audra.utils.PageInput;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import digital.windmill.audra.graphql.type.input.AssetWhereInput;
import digital.windmill.audra.graphql.type.input.AssetsInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@Slf4j
public class AssetIt extends AbstractIntegrationTest {

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
        var where = AssetsInput.builder().where(
                        AssetWhereInput.builder()
                                .build())
                .build();
        var input = objectMapper.createObjectNode().putPOJO("input", where);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getAssets.graphql", input);

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
                                .query("Dell")
                                .type(NodesInput.of("5478b586-e607-4448-ac05-3e5f2adbbc1b"))
                                .assignee(NodesInput.of("6bac1755-c88c-4462-ae14-527b54b03e0d"))
                                .location(NodesInput.of("cabfb51b-41c5-4f4d-afd5-146c796391af"))
                                .build())
                .build();
        var input = objectMapper.createObjectNode().putPOJO("input", where);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getAssets.graphql", input);

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAssetsFiltered.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Kelly Danielle", "ell Kel", "danielle"})
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAssetsByQuery(String searchText) throws IOException, URISyntaxException {
        var where = AssetsInput.builder().where(
                        AssetWhereInput.builder()
                                .query(searchText)
                                .build())
                .build();
        var input = objectMapper.createObjectNode().putPOJO("input", where);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getAssets.graphql", input);

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/getAssetsByQuery.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAssetsPaginated() throws IOException, URISyntaxException {
        var where = AssetsInput.builder().pagination(
                        PageInput.builder()
                                .pageNumber(3)
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

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldUpdateAssetAsArchive() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/request/updateAssetAsArchive.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/updateAssetAsArchive.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

}
