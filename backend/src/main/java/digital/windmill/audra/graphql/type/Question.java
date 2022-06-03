package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Question implements Node {

    private UUID id;
    private String body;
    private QuestionType type;
}
