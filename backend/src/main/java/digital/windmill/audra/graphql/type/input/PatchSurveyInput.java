package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatchSurveyInput {
    private UUID id;
    private String title;
    private String description;
    private List<CreateQuestionInput> questions;
}
