package digital.windmill.audra.graphql.type;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionOption implements Node {
    private UUID id;
    private String text;
}
