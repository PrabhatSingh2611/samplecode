package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.PolicyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Policy implements Node {
    private UUID uuid;
    private String title;
    private Resource file;
    private ZonedDateTime publicationDate;
    private PolicyStatus status;
    private Employee owner;
}
