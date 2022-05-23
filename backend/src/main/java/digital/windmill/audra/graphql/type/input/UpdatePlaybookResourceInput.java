package digital.windmill.audra.graphql.type.input;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UpdatePlaybookResourceInput {
    private ResourceInput resource;
}
