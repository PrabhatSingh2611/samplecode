package digital.windmill.audra.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import digital.windmill.audra.dao.entity.LeaveRequestEntity;
import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.DateTimeMapperImpl;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapperImpl;
import digital.windmill.audra.graphql.mapper.LeaveRequestMapperImpl;
import digital.windmill.audra.graphql.mapper.LeaveTypeMapper;
import digital.windmill.audra.graphql.mapper.LeaveTypeMapperImpl;
import digital.windmill.audra.graphql.type.input.CreateLeaveRequestInput;
import digital.windmill.audra.graphql.type.input.DatePeriod;
import digital.windmill.audra.graphql.type.input.PatchLeaveRequestInput;

@ExtendWith(MockitoExtension.class)
class LeaveRequestMapperTest {

    private static final UUID LEAVE_REQUEST_UUID = UUID.randomUUID();
    private static final String COMMENT = "comment";
    private static final String UPDATED_COMMENT = "updated comment";
    private static final LeaveRequestStatus STATUS = LeaveRequestStatus.NEW;
    private static final Instant REQUEST_DATE = Instant.now().minusSeconds(1800);
    private static final Instant START_DATE = Instant.now();
    private static final Instant UPDATED_START_DATE = Instant.now();
    private static final Instant END_DATE = Instant.now().plusSeconds(3600);
    private static final Instant UPDATED_END_DATE = Instant.now().plusSeconds(3600);

    @Spy
    private LeaveTypeMapper leaveTypeMapper = new LeaveTypeMapperImpl();
    @Spy
    private EmployeeMapper employeeMapper = new EmployeeMapperImpl();
    @Spy
    private DateTimeMapper dateTimeMapper = new DateTimeMapperImpl();
    @InjectMocks
    private LeaveRequestMapperImpl mapper;


    @Test
    void shouldMapLeaveRequestEntityToLeaveRequest() {

        var result = mapper.mapLeaveRequestEntityToLeaveRequest(createLeaveRequestEntity());

        assertAll(
                () -> assertEquals(LEAVE_REQUEST_UUID, result.getId()),
                () -> assertEquals(COMMENT, result.getComment()),
                () -> assertEquals(STATUS, result.getStatus()),
                () -> assertEquals(toDateTime(START_DATE), result.getStartDate()),
                () -> assertEquals(toDateTime(END_DATE), result.getEndDate())
        );
    }

    @Test
    void shouldMapCreateLeaveRequestInputToLeaveRequestEntity() {

        var result = mapper.mapCreateLeaveRequestInputToLeaveRequestEntity(createLeaveRequestInput());

        assertAll(
                () -> assertEquals(COMMENT, result.getComment()),
                () -> assertEquals(STATUS, result.getStatus()),
                () -> assertEquals(START_DATE, result.getStartDate()),
                () -> assertEquals(END_DATE, result.getEndDate())
        );
    }

    private DatePeriod createDatePeriod() {
        return new DatePeriod(START_DATE.atZone(ZoneId.of("UTC")), END_DATE.atZone(ZoneId.of("UTC")));
    }

    @ParameterizedTest
    @MethodSource("patchTestData")
    void shouldMapPatchLeaveRequestInputToLeaveRequestEntity(LeaveRequestEntity current, PatchLeaveRequestInput input, LeaveRequestEntity expected) {
        var result = mapper.map(input, current);
        assertEquals(expected.getUuid(), result.getUuid());
        assertEquals(expected.getStatus(), result.getStatus());
        assertEquals(expected.getComment(), result.getComment());
        assertEquals(expected.getStartDate(), result.getStartDate());
        assertEquals(expected.getEndDate(), result.getEndDate());
    }

    private static Stream<Arguments> patchTestData() {
        return Stream.of(Arguments.of(
                    // result should be same as patch input empty
                    createLeaveRequestEntity(),
                    PatchLeaveRequestInput.builder().build(),
                    createLeaveRequestEntity()
                ),
                Arguments.of(
                    // should not update entity uuid
                    createLeaveRequestEntity(),
                    PatchLeaveRequestInput.builder().id(UUID.randomUUID()).build(),
                    createLeaveRequestEntity()
                ),
                Arguments.of(
                    // should update field according to patch input
                    createLeaveRequestEntity(LEAVE_REQUEST_UUID, COMMENT, REQUEST_DATE, START_DATE, END_DATE, LeaveRequestStatus.NEW),
                    PatchLeaveRequestInput.builder()
                        .id(UUID.randomUUID())
                        .status(LeaveRequestStatus.APPROVED)
                        .comment(UPDATED_COMMENT)
                        .period(new DatePeriod(toDateTime(UPDATED_START_DATE), toDateTime(UPDATED_END_DATE)))
                        .build(),
                    createLeaveRequestEntity(LEAVE_REQUEST_UUID, UPDATED_COMMENT, REQUEST_DATE, UPDATED_START_DATE, UPDATED_END_DATE, LeaveRequestStatus.APPROVED)
                )
                );
    }

    private CreateLeaveRequestInput createLeaveRequestInput() {
        return CreateLeaveRequestInput
                .builder()
                .comment(COMMENT)
                .status(STATUS)
                .period(createDatePeriod())
                .build();
    }

    private static ZonedDateTime toDateTime(Instant datetime) {
        return ZonedDateTime.ofInstant(datetime, ZoneId.of("UTC"));
    }

    private static LeaveRequestEntity createLeaveRequestEntity() {
        return createLeaveRequestEntity(LEAVE_REQUEST_UUID, COMMENT, REQUEST_DATE, START_DATE, END_DATE, LeaveRequestStatus.NEW);
    }

    private static LeaveRequestEntity createLeaveRequestEntity(UUID uuid, String comment, Instant requestDate, Instant startDate, Instant endDate, LeaveRequestStatus status) {
        LeaveRequestEntity l = new LeaveRequestEntity();
        l.setUuid(uuid);
        l.setComment(comment);
        l.setRequestDate(requestDate);
        l.setStartDate(startDate);
        l.setEndDate(endDate);
        l.setStatus(status);
        return l;
    }

}