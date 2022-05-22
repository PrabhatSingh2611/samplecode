package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.CandidateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyCandidateWhereInput {
    private NodeInput vacancy;
    private List<CandidateStatus> status;
}
