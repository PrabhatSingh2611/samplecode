package digital.windmill.audra;

import com.fasterxml.jackson.databind.ObjectMapper;
import digital.windmill.audra.graphql.type.DeletePlaybookSectionInput;
import digital.windmill.audra.graphql.type.PatchPlaybookSectionInput;
import digital.windmill.audra.graphql.type.ReorderPlaybookSectionInput;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionInput;
import digital.windmill.audra.graphql.type.input.NodeInput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.util.UUID;

@Slf4j
class PlaybookSectionIt extends AbstractIntegrationTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldCreatePlaybookSection() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new CreatePlaybookSectionInput() {{
            setName("name");
            setPlaybook(NodeInput.builder().id(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2400")).build());
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_section/createPlaybookSection.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("name", response.get("$.data.createPlaybookSection.section.name"));
        Assertions.assertNotNull(response.get("$.data.createPlaybookSection.section.id"));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldPatchPlaybookSection() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new PatchPlaybookSectionInput() {{
            setName("patched name");
            setId(UUID.fromString("d0228476-aa22-4f2a-95ab-ee7bd0178330"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_section/patchPlaybookSection.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("patched name", response.get("$.data.patchPlaybookSection.section.name"));
        Assertions.assertEquals("d0228476-aa22-4f2a-95ab-ee7bd0178330", response.get("$.data.patchPlaybookSection.section.id"));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldDeletePlaybookSection() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new DeletePlaybookSectionInput() {{
            setId(UUID.fromString("d0228476-aa22-4f2a-95ab-ee7bd0178330"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_section/deletePlaybookSection.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("d0228476-aa22-4f2a-95ab-ee7bd0178330", response.get("$.data.deletePlaybookSection.section.id"));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldMoveSectionToFirstPosition() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new ReorderPlaybookSectionInput() {{
            setId(UUID.fromString("e88c0f10-a84b-4bfa-a8d4-bfe924874224"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_section/reorderPlaybookSection.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("e88c0f10-a84b-4bfa-a8d4-bfe924874224", response.get("$.data.reorderPlaybookSection.playbook.sections[0].id"));
        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2405", response.get("$.data.reorderPlaybookSection.playbook.sections[1].id"));
        Assertions.assertEquals("91b240f7-087f-4d78-9907-66a1e4f14342", response.get("$.data.reorderPlaybookSection.playbook.sections[2].id"));
        Assertions.assertEquals("2d71d9b3-874d-416c-a874-9275886d0a19", response.get("$.data.reorderPlaybookSection.playbook.sections[3].id"));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldMoveSectionToSecondPosition() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new ReorderPlaybookSectionInput() {{
            setId(UUID.fromString("e88c0f10-a84b-4bfa-a8d4-bfe924874224"));
            setAfterId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2405"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_section/reorderPlaybookSection.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2405", response.get("$.data.reorderPlaybookSection.playbook.sections[0].id"));
        Assertions.assertEquals("e88c0f10-a84b-4bfa-a8d4-bfe924874224", response.get("$.data.reorderPlaybookSection.playbook.sections[1].id"));
        Assertions.assertEquals("91b240f7-087f-4d78-9907-66a1e4f14342", response.get("$.data.reorderPlaybookSection.playbook.sections[2].id"));
        Assertions.assertEquals("2d71d9b3-874d-416c-a874-9275886d0a19", response.get("$.data.reorderPlaybookSection.playbook.sections[3].id"));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldMoveSectionToLastPosition() throws IOException {
        var input = objectMapper.createObjectNode().putPOJO("input", new ReorderPlaybookSectionInput() {{
            setId(UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2405"));
            setAfterId(UUID.fromString("2d71d9b3-874d-416c-a874-9275886d0a19"));
        }});

        var response = graphQLTestTemplate.perform("graphql/request/playbook_section/reorderPlaybookSection.graphql", input);

        log.info("Input: " + input.toPrettyString());
        log.info("Response: " + response.readTree().toPrettyString());

        Assertions.assertEquals("91b240f7-087f-4d78-9907-66a1e4f14342", response.get("$.data.reorderPlaybookSection.playbook.sections[0].id"));
        Assertions.assertEquals("e88c0f10-a84b-4bfa-a8d4-bfe924874224", response.get("$.data.reorderPlaybookSection.playbook.sections[1].id"));
        Assertions.assertEquals("2d71d9b3-874d-416c-a874-9275886d0a19", response.get("$.data.reorderPlaybookSection.playbook.sections[2].id"));
        Assertions.assertEquals("d7a9e9c8-fa56-47ce-b036-678e6f7f2405", response.get("$.data.reorderPlaybookSection.playbook.sections[3].id"));
    }

}
