package digital.windmill.audra.graphql.type.input;

import digital.windmill.audra.dao.entity.enums.CandidateStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatchCandidateInput {
    private UUID id;
    private UUID vacancy;
    private String firstName;
    private String lastName;
    private String linkedIn;
    private NodesInput attachments;
    private CandidateStatus status;
}

