package digital.windmill.audra;

import com.graphql.spring.boot.test.GraphQLResponse;
import digital.windmill.audra.graphql.type.ResourcePayload;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.ResponseExtractor;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Testcontainers
class ResourceIt extends AbstractIntegrationTest {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo"));

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Value("${storage.destination.url}")
    protected String storageUrl;

    @Test
    @Sql(statements = "DELETE FROM resource;")
    void shouldStoreResource() throws IOException, URISyntaxException {
        UUID uuidOfPrevResource = findUuidOfLastAddedResource();
        assertNull(uuidOfPrevResource, "Any resources were added before");

        ResourcePayload uploadResponse = uploadResourceFromClassPath("pdf-test.pdf");

        UUID lastAddedResourceUuid = findUuidOfLastAddedResource();
        assertNotNull(uploadResponse.getResource());
        assertEquals(lastAddedResourceUuid, uploadResponse.getResource().getId(), "Last added resource's uuid should be same as in response");
        assertTrue(uploadResponse.getResource().getUrl().startsWith(storageUrl), "Url should starts with url path from configuration");
        assertTrue(uploadResponse.getResource().getUrl().endsWith(lastAddedResourceUuid.toString()), "Url should ends with resource uuid");
        assertTrue(uploadResponse.getResource().getThumbnail().startsWith(storageUrl), "Thumbnail should be generated automatically");
        assertEquals("application/pdf", uploadResponse.getResource().getMimeType());
    }

    @Test
    void shouldGetUploadedResourceByUuid() throws IOException, URISyntaxException {

        ResourcePayload uploadedResponse = uploadResourceFromClassPath("pdf-test.pdf");
        var input = objectMapper.createObjectNode().putPOJO("id", uploadedResponse.getResource().getId());
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/getResource.graphql", input);

        log.info(response.readTree().toPrettyString());

        ResourcePayload actual = response.get("$.data.resource", ResourcePayload.class);

        assertNotNull(actual.getResource());
        assertEquals(uploadedResponse.getResource().getId(), actual.getResource().getId());
        assertEquals(uploadedResponse.getResource().getUrl(), actual.getResource().getUrl());
        assertEquals("application/pdf", actual.getResource().getMimeType());
        assertEquals(uploadedResponse.getResource().getThumbnail(), actual.getResource().getThumbnail());
    }

    @Test
    void shouldReturnResourceBodyByUuid() throws IOException, URISyntaxException {
        String fileNameToUpload = "graphql/request/uploadResource.graphql";
        String fileBody = readFromResource("graphql/request/uploadResource.graphql");
        ResourcePayload uploadedResponse = uploadResourceFromClassPath(fileNameToUpload);
        var url = URI.create(uploadedResponse.getResource().getUrl()).getPath();

        ResponseExtractor<String> respextractor = clientHttpResponse -> new String(clientHttpResponse.getBody().readAllBytes());
        String actualFileBody = restTemplate.execute(url, HttpMethod.GET, null, respextractor);

        assertEquals(fileBody, actualFileBody, "Checking that file body wasn't changed during uploading/downloading");
    }

    private ResourcePayload uploadResourceFromClassPath(String pathInClassPath) throws IOException, URISyntaxException {
        var variables = new LinkedHashMap<>();
        variables.put("file", null);

        var query = readFromResource("graphql/request/uploadResource.graphql");

        var params = new LinkedHashMap<>();
        params.put("variables", variables);
        params.put("query", query);

        ClassPathResource fileResource = new ClassPathResource(pathInClassPath);
        var formData = new LinkedMultiValueMap<>();
        formData.set("operations", objectMapper.writeValueAsString(params));
        formData.set("map", "{\"file\": [\"variables.file\"]}");
        formData.set("file", fileResource);

        var requestEntity = new HttpEntity<>(formData);
        var response = restTemplate.exchange("/graphql", HttpMethod.POST, requestEntity, UploadDataRestResponse.class);
        log.info(response.toString());
        return Optional.ofNullable(response)
                .map(ResponseEntity::getBody)
                .map(UploadDataRestResponse::getData)
                .map(UploadRestResponse::getUploadResource)
                .orElse(null);
    }

    private UUID findUuidOfLastAddedResource() {
        return DataAccessUtils.singleResult(jdbcTemplate.query("select * from resource order by id desc limit 1", new RowMapper<UUID>() {
            @Override
            public UUID mapRow(ResultSet rs, int rowNum) throws SQLException {
                return UUID.fromString(rs.getString("uuid"));
            }
        }));
    }

    @Data
    private static class UploadDataRestResponse {
        private UploadRestResponse data;
    }

    @Data
    private static class UploadRestResponse {
        private ResourcePayload uploadResource;
    }

}
