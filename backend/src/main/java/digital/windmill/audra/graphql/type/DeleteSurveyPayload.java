package digital.windmill.audra.graphql.type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteSurveyPayload {
    private Node survey;
}
