package digital.windmill.audra.graphql.type;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LeaveTypeConnectionPayload {
    private List<LeaveType> items;
    private PageInfo pageInfo;
    private Long totalItems;
}
