package digital.windmill.audra.graphql.type;

import digital.windmill.audra.dao.entity.enums.CandidateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Candidate implements Node {
    private UUID id;
    private Vacancy vacancy;
    private String firstName;
    private String lastName;
    private String linkedIn;
    private List<Resource> attachments;
    private CandidateStatus status;
}
