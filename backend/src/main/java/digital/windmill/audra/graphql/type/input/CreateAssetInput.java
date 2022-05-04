package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAssetInput {
    private String title;
    private String serial;
    private UUID type;
    private UUID employee;
    private ZonedDateTime archivedDate;
    private ZonedDateTime purchasedDate;
    private ZonedDateTime nextActionDate;
}
