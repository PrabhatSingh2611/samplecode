package digital.windmill.audra.graphql.type.input;

import lombok.Builder;
import lombok.Data;

import java.net.URL;

@Data
@Builder
public class UpdatePlaybookVideoInput {
    private URL url;
    private String description;
}
