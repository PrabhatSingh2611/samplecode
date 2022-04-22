package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.ObjectiveEntity;
import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;

import digital.windmill.audra.graphql.type.Objective;
import digital.windmill.audra.graphql.type.input.CreateObjectiveInput;
import digital.windmill.audra.graphql.type.input.EmployeeObjectiveInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ObjectiveMapperTest<ObjectiveMapperImpl> {

    @InjectMocks
    private ObjectiveMapperImpl objectiveMapper;

    private static final Long ID= 1l;
    private static final UUID TEST_UUID = UUID.fromString("40aab8f6-271b-42de-867b-e65cc31dc90f");
    private static final String FIRST_NAME = "Firstname";
    private static final String LAST_NAME = "Lastname";
    private static final String NAME = "graphql";
    private static final String COMMENTS = "schema,etc";
    private static final String DESCRIPTION = "learning graphql";
    private static final Instant DUE_TO_DATE = Instant.parse("2022-07-28T12:03:00.480425Z");
    final static Instant LOCAL_DATE = Instant.now();
    private static final ZonedDateTime DUE_TO_DATE_ZONED = ZonedDateTime.parse("2022-07-28T12:03:00.480425Z");
    private static final String POSITION = "Position";
    private final static ZonedDateTime DATE_TIME = ZonedDateTime.now();

   /* @Test
    void shouldMapInputToEntity(){
       ObjectiveEntity entity = objectiveMapper.mapInputToEntity(createObjectiveInput(),createEmployeeEntity());
        assertAll(
                () -> assertEquals(COMMENTS, entity.getComments()),
                () -> assertEquals(NAME, entity.getName())
        );

    }*/

   /* @Test
    void shouldMap(){
      Objective objective =  objectiveMapper.map(createObjectiveEntity());
        assertAll(
                () -> assertEquals(COMMENTS, objective.getComments()),
                () -> assertEquals(NAME, objective.getName())
        );
    }*/

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(ID);
        e.setUuid(TEST_UUID);
        e.setFirstName(FIRST_NAME);
        e.setLastName(LAST_NAME);
        e.setBirthday(LOCAL_DATE);

        return e;
    }

    private ObjectiveEntity createObjectiveEntity(){
        return ObjectiveEntity.builder()
                .uuid(UUID.randomUUID())
                .name(NAME)
                .comments(COMMENTS)
                .description(DESCRIPTION)
                .dueToDate(DUE_TO_DATE)
                .status(ObjectiveStatus.NEW)
                .build();
    }

    private CreateObjectiveInput createObjectiveInput() {
        return CreateObjectiveInput.builder()
                .name(NAME)
                .comments(COMMENTS)
                .description(DESCRIPTION)
                .dueToDate(DUE_TO_DATE_ZONED)
                .employee(EmployeeObjectiveInput.builder().uuid(TEST_UUID).build())
                .status(ObjectiveStatus.NEW)
                .build();
    }
}
