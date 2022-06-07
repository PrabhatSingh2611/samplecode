package digital.windmill.audra.graphql.resolver.playbook;

import digital.windmill.audra.graphql.facade.impl.PlaybookFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Playbook;
import digital.windmill.audra.graphql.type.input.PlaybookInput;
import digital.windmill.audra.graphql.type.input.PlaybookPayload;
import digital.windmill.audra.graphql.type.input.PlaybooksInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaybookQueryResolver implements GraphQLQueryResolver {

    private PlaybookFacade playbookFacade;

    public PlaybookPayload playbook(PlaybookInput playbookInput) {
        return PlaybookPayload.builder()
                .playbook(playbookFacade.findPlaybookByUuid(playbookInput.getId()))
                .build();
    }

    public ConnectionPayload<Playbook> playbooks(PlaybooksInput input) {
        return ConnectionUtils.buildPayload(playbookFacade.getPlaybooks(input));
    }
}
