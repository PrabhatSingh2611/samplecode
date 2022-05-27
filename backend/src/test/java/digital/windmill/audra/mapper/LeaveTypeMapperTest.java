package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.LeaveTypeEntity;
import digital.windmill.audra.dao.entity.enums.EndOfYearAction;
import digital.windmill.audra.graphql.mapper.LeaveTypeMapperImpl;
import digital.windmill.audra.graphql.type.LeaveTypeEndOfYearAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LeaveTypeMapperTest {

    private static final UUID TEST_UUID = UUID.fromString("045c7bac-ea83-4bbf-ad20-aeea59db480a");
    private static final Long ID = 22L;
    private static final String NAME = "Name";
    private static final Integer DAYS = 5;
    private static final LeaveTypeEndOfYearAction endOfYearAction = LeaveTypeEndOfYearAction.MOVE;
    private static final EndOfYearAction ACTION = EndOfYearAction.CARRY_OVER;

    @InjectMocks
    private LeaveTypeMapperImpl mapper;

    @Test
    void shouldMapLeaveTypeEntityToLeaveType() {

        var result = mapper.map(createLeaveTypeEntity());

        assertAll(
                () -> assertEquals(NAME, result.getName()),
                () -> assertEquals(DAYS, result.getDays()),
                () -> assertEquals(endOfYearAction, result.getEndOfYearAction())
        );
    }

    private LeaveTypeEntity createLeaveTypeEntity() {
        LeaveTypeEntity l = new LeaveTypeEntity();
        l.setId(ID);
        l.setUuid(TEST_UUID);
        l.setName(NAME);
        l.setDays(DAYS);
        l.setAction(ACTION);
        return l;
    }
}
