package digital.windmill.audra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import digital.windmill.audra.graphql.type.LeaveRequest;
import digital.windmill.audra.graphql.type.input.LeaveRequestsSortEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LeaveRequestIt extends AbstractIntegrationTest {

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnLeaveRequest() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/leave_request/getLeaveRequest.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = readFromResource("graphql/response/leave_request/getLeaveRequest.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllLeaveRequestByEmployee() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/leave_request/getAllLeaveRequestByEmployee.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = readFromResource("graphql/response/leave_request/getAllLeaveRequestByEmployee.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllLeaveRequestByApprover() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/leave_request/getAllLeaveRequestByApprover.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = readFromResource("graphql/response/leave_request/getAllLeaveRequestByApprover.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldReturnAllLeaveRequestByStartAndEndDate() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/leave_request/getAllLeaveRequestByStartAndEndDate.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = readFromResource("graphql/response/leave_request/getAllLeaveRequestByStartAndEndDate.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldCreateLeaveRequest() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/leave_request/createLeaveRequest.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = readFromResource("graphql/response/leave_request/createLeaveRequest.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldPatchLeaveRequest() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate.
                postForResource("graphql/request/leave_request/patchLeaveRequest.graphql");
        log.info(response.readTree().toPrettyString());
        String jsonString = readFromResource("graphql/response/leave_request/patchLeaveRequest.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldDeleteLeaveRequest() throws IOException, URISyntaxException {
        GraphQLResponse response = graphQLTestTemplate
                .postForResource("graphql/request/leave_request/deleteLeaveRequest.graphql");

        log.info(response.readTree().toPrettyString());

        String jsonString = readFromResource("graphql/response/leave_request/deleteLeaveRequest.json");
        JsonNode expectedJson = objectMapper.readTree(jsonString);
        assertEquals(expectedJson, response.get("$", JsonNode.class));
    }

    @Test
    @Sql("classpath:/db/insert-initial-entities.sql")
    void shouldPatchLeaveRequestWithApproveStatus() throws IOException, URISyntaxException {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.putPOJO("id", "493a5be9-01ba-47c6-95c1-29c230528527");
        objectNode.putPOJO("status", "APPROVED");
        ObjectNode input = JsonNodeFactory.instance.objectNode();
        input.set("input", objectNode);
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/leave_request/patchLeaveRequestTemplate.graphql", input );
        log.info(response.readTree().toPrettyString());

        LeaveRequest actualLeaveRequest = response.get("$.data.patchLeaveRequest.leaveRequest", LeaveRequest.class);
        assertNotNull(actualLeaveRequest);
        assertEquals(UUID.fromString("493a5be9-01ba-47c6-95c1-29c230528527"), actualLeaveRequest.getId());
        assertEquals(LeaveRequestStatus.APPROVED, actualLeaveRequest.getStatus());
    }

    @Nested
    @Sql({"classpath:/db/delete-all.sql",
        "classpath:/db/insert-leave-requests-for-sorting.sql"})
    class SortingTest {
        @Test
        void shouldReturnLeaveRequestsAccordingSortOrder_byCreatedAtDesc() throws IOException {
            ObjectNode sortVariable = JsonNodeFactory.instance.objectNode();
            sortVariable.putPOJO("sort", List.of(LeaveRequestsSortEnum.startDate_DESC));
            GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/leave_request/getSortedLeaveRequests.graphql", sortVariable );
            log.info(response.readTree().toPrettyString());
            assertLeaveRequestUuids(List.of(
                    UUID.fromString("3c0e4949-d259-4e1d-86a5-e6025308346f"),
                    UUID.fromString("c823dbfb-b1c6-4c77-b22e-fc06f2737b2b"),
                    UUID.fromString("493a5be9-01ba-47c6-95c1-29c230528527"),
                    UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2399")), response);
        }

        @Test
        void shouldReturnLeaveRequestsAccordingSortOrder_byCreatedAtAsc() throws IOException {
            ObjectNode sortVariable = JsonNodeFactory.instance.objectNode();
            sortVariable.putPOJO("sort", List.of(LeaveRequestsSortEnum.startDate_ASC));
            GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/leave_request/getSortedLeaveRequests.graphql", sortVariable );
            log.info(response.readTree().toPrettyString());
            assertLeaveRequestUuids(List.of(
                    UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2399"),
                    UUID.fromString("493a5be9-01ba-47c6-95c1-29c230528527"),
                    UUID.fromString("c823dbfb-b1c6-4c77-b22e-fc06f2737b2b"),
                    UUID.fromString("3c0e4949-d259-4e1d-86a5-e6025308346f")
                    ), response);
        }

        @Test
        void shouldReturnLeaveRequestsAccordingSortOrder_byPendingFirstCreatedAtAsc() throws IOException {
            ObjectNode sortVariable = JsonNodeFactory.instance.objectNode();
            sortVariable.putPOJO("sort", List.of(LeaveRequestsSortEnum.pending_DESC, LeaveRequestsSortEnum.startDate_ASC));
            GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/leave_request/getSortedLeaveRequests.graphql", sortVariable );
            log.info(response.readTree().toPrettyString());
            assertLeaveRequestUuids(List.of(
                    UUID.fromString("493a5be9-01ba-47c6-95c1-29c230528527"),
                    UUID.fromString("3c0e4949-d259-4e1d-86a5-e6025308346f"),
                    UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2399"),
                    UUID.fromString("c823dbfb-b1c6-4c77-b22e-fc06f2737b2b")
                    ), response);
        }

        @Test
        void shouldReturnLeaveRequestsAccordingSortOrder_byPendingFirstCreatedAtDesc() throws IOException {
            ObjectNode sortVariable = JsonNodeFactory.instance.objectNode();
            sortVariable.putPOJO("sort", List.of(LeaveRequestsSortEnum.pending_DESC, LeaveRequestsSortEnum.startDate_DESC));
            GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/leave_request/getSortedLeaveRequests.graphql", sortVariable );
            log.info(response.readTree().toPrettyString());
            assertLeaveRequestUuids(List.of(
                    UUID.fromString("3c0e4949-d259-4e1d-86a5-e6025308346f"),
                    UUID.fromString("493a5be9-01ba-47c6-95c1-29c230528527"),
                    UUID.fromString("c823dbfb-b1c6-4c77-b22e-fc06f2737b2b"),
                    UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2399")
                    ), response);
        }

        @Test
        void shouldReturnLeaveRequestsAccordingSortOrder_byPendingLastCreatedAtAsc() throws IOException {
            ObjectNode sortVariable = JsonNodeFactory.instance.objectNode();
            sortVariable.putPOJO("sort", List.of(LeaveRequestsSortEnum.pending_ASC, LeaveRequestsSortEnum.startDate_ASC));
            GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/leave_request/getSortedLeaveRequests.graphql", sortVariable );
            log.info(response.readTree().toPrettyString());
            assertLeaveRequestUuids(List.of(
                    UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2399"),
                    UUID.fromString("c823dbfb-b1c6-4c77-b22e-fc06f2737b2b"),
                    UUID.fromString("493a5be9-01ba-47c6-95c1-29c230528527"),
                    UUID.fromString("3c0e4949-d259-4e1d-86a5-e6025308346f")
                    ), response);
        }

        @Test
        void shouldReturnLeaveRequestsAccordingSortOrder_byPendingLastCreatedAtDesc() throws IOException {
            ObjectNode sortVariable = JsonNodeFactory.instance.objectNode();
            sortVariable.putPOJO("sort", List.of(LeaveRequestsSortEnum.pending_ASC, LeaveRequestsSortEnum.startDate_DESC));
            GraphQLResponse response = graphQLTestTemplate.perform("graphql/request/leave_request/getSortedLeaveRequests.graphql", sortVariable );
            log.info(response.readTree().toPrettyString());
            assertLeaveRequestUuids(List.of(
                    UUID.fromString("c823dbfb-b1c6-4c77-b22e-fc06f2737b2b"),
                    UUID.fromString("d7a9e9c8-fa56-47ce-b036-678e6f7f2399"),
                    UUID.fromString("3c0e4949-d259-4e1d-86a5-e6025308346f"),
                    UUID.fromString("493a5be9-01ba-47c6-95c1-29c230528527")
                    ), response);
        }

        private void assertLeaveRequestUuids(List<UUID> expectedUuids, GraphQLResponse response) {
            List<LeaveRequest> list = response.getList("$.data.leaveRequests.items", LeaveRequest.class);
            List<UUID> actualRequestUuids = list.stream().map(LeaveRequest::getId).toList();
            Assertions.assertEquals(expectedUuids, actualRequestUuids);
        }
    }
}
