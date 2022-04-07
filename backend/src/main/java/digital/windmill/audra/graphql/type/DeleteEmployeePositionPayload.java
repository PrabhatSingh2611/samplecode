package digital.windmill.audra.graphql.type;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DeleteEmployeePositionPayload{
    private Node employeePosition;
}
