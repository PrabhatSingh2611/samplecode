package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuestionInput {
    private String body;
    private QuestionType type;
    private List<CreateQuestionOptionInput> options;
}
