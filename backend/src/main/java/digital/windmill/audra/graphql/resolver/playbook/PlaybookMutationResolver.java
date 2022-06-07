package digital.windmill.audra.graphql.resolver.playbook;

import digital.windmill.audra.graphql.facade.impl.PlaybookFacade;
import digital.windmill.audra.graphql.type.DeletePlaybookPayload;
import digital.windmill.audra.graphql.type.input.CreatePlaybookInput;
import digital.windmill.audra.graphql.type.input.DeletePlaybookInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookInput;
import digital.windmill.audra.graphql.type.input.PlaybookPayload;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaybookMutationResolver implements GraphQLMutationResolver {

    private PlaybookFacade playbookFacade;

    public PlaybookPayload createPlaybook(CreatePlaybookInput input) {
        return PlaybookPayload.builder()
                .playbook(playbookFacade.createPlaybook(input))
                .build();
    }

    public PlaybookPayload patchPlaybook(PatchPlaybookInput input) {
        return PlaybookPayload.builder()
                .playbook(playbookFacade.patchPlaybook(input))
                .build();
    }

    public DeletePlaybookPayload deletePlaybook(DeletePlaybookInput input) {
        return DeletePlaybookPayload.builder()
                .playbook(playbookFacade.deletePlaybook(input)).
                build();
    }
}
