package digital.windmill.audra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

@ActiveProfiles("test")
@SpringBootTest(classes = TestConfig.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
public class AbstractIntegrationTest extends PostgresIntegrationTest {

    @Autowired
    protected GraphQLTestTemplate graphQLTestTemplate;
    @Autowired
    protected ObjectMapper objectMapper;

    protected String readFromResource(String path) throws IOException, URISyntaxException {
        return Files.readString(Paths.get(resourceUri(path)), StandardCharsets.UTF_8);
    }

    protected JsonNode readFromResourceAsJsonNode(String path) throws IOException, URISyntaxException {
        return objectMapper.readTree(readFromResource(path));
    }

    protected URI resourceUri(String path) throws URISyntaxException, FileNotFoundException { 
        try {
            return AbstractIntegrationTest
                    .class
                    .getClassLoader()
                    .getResource(path)
                    .toURI();
        } catch (NullPointerException e) {
            throw new FileNotFoundException("Resource by path " + path + " couldn't be found!");
        }
    }
}