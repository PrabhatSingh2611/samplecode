package digital.windmill.audra;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.DeletePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.ReorderPlaybookSectionTopicInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
class PlaybookTopicIt extends AbstractIntegrationTest {


    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnPlaybookTopic() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/playbook_topic/getPlaybookTopic.graphql");

        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/playbook_topic/getPlaybookTopic.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }


    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldCreatePlaybookSectionTopic() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new CreatePlaybookSectionTopicInput() {{
            setName("name");
            setSection(NodeInput.builder().id(UUID.fromString("d0228476-aa22-4f2a-95ab-ee7bd0178330")).build());
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_topic/createPlaybookSectionTopic.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("name", response.get("$.data.createPlaybookSectionTopic.topic.name"));
        Assertions.assertNotNull(response.get("$.data.createPlaybookSectionTopic.topic.id"));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldPatchPlaybookSectionTopic() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new PatchPlaybookSectionTopicInput() {{
            setName("patched name");
            setBody("patched body");
            setId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2406"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_topic/patchPlaybookSectionTopic.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("patched name", response.get("$.data.patchPlaybookSectionTopic.topic.name"));
        Assertions.assertEquals("patched body", response.get("$.data.patchPlaybookSectionTopic.topic.body"));
        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2406", response.get("$.data.patchPlaybookSectionTopic.topic.id"));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldDeletePlaybookSectionTopic() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new DeletePlaybookSectionTopicInput() {{
            setId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2406"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_topic/deletePlaybookSectionTopic.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2406", response.get("$.data.deletePlaybookSectionTopic.topic.id"));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReorderPlaybookSectionTopicToDifferentSection() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new ReorderPlaybookSectionTopicInput() {{
            setId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2406"));
            setAfterId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2407"));
            setSectionId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2405"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_topic/reorderPlaybookSectionTopic.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2405", response.get("$.data.reorderPlaybookSectionTopic.section.id"));
        Assertions.assertEquals("Section 1 Playbook 1", response.get("$.data.reorderPlaybookSectionTopic.section.name"));

    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldMoveTopicToFirstPosition() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new ReorderPlaybookSectionTopicInput() {{
            setId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2409"));
            setSectionId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2405"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_topic/reorderPlaybookSectionTopic.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2405", response.get("$.data.reorderPlaybookSectionTopic.section.id"));
        Assertions.assertEquals("Section 1 Playbook 1", response.get("$.data.reorderPlaybookSectionTopic.section.name"));

        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2409", response.get("$.data.reorderPlaybookSectionTopic.section.topics[0].id"));
        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2407", response.get("$.data.reorderPlaybookSectionTopic.section.topics[1].id"));
        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2408", response.get("$.data.reorderPlaybookSectionTopic.section.topics[2].id"));

    }


    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldMoveTopicToSecondPosition() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new ReorderPlaybookSectionTopicInput() {{
            setId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2409"));
            setAfterId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2407"));
            setSectionId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2405"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_topic/reorderPlaybookSectionTopic.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2405", response.get("$.data.reorderPlaybookSectionTopic.section.id"));
        Assertions.assertEquals("Section 1 Playbook 1", response.get("$.data.reorderPlaybookSectionTopic.section.name"));

        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2407", response.get("$.data.reorderPlaybookSectionTopic.section.topics[0].id"));
        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2409", response.get("$.data.reorderPlaybookSectionTopic.section.topics[1].id"));
        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2408", response.get("$.data.reorderPlaybookSectionTopic.section.topics[2].id"));

    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldMoveTopicToLastPosition() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new ReorderPlaybookSectionTopicInput() {{
            setId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2409"));
            setAfterId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2408"));
            setSectionId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2405"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_topic/reorderPlaybookSectionTopic.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2405", response.get("$.data.reorderPlaybookSectionTopic.section.id"));
        Assertions.assertEquals("Section 1 Playbook 1", response.get("$.data.reorderPlaybookSectionTopic.section.name"));

        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2407", response.get("$.data.reorderPlaybookSectionTopic.section.topics[0].id"));
        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2408", response.get("$.data.reorderPlaybookSectionTopic.section.topics[1].id"));
        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2409", response.get("$.data.reorderPlaybookSectionTopic.section.topics[2].id"));

    }

}
