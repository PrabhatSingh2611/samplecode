package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaybookVideoInput {
    private URL url;
    private String description;
}