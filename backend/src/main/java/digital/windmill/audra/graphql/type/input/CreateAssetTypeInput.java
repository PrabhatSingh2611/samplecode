package digital.windmill.audra.graphql.type.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAssetTypeInput {
    private String title;
    private String icon;
}
