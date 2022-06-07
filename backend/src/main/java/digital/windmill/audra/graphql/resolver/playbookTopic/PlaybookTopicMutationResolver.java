package digital.windmill.audra.graphql.resolver.playbookTopic;

import digital.windmill.audra.graphql.facade.impl.PlaybookTopicFacade;
import digital.windmill.audra.graphql.type.DeletePlaybookSectionTopicPayload;
import digital.windmill.audra.graphql.type.PlaybookSectionPayload;
import digital.windmill.audra.graphql.type.PlaybookSectionTopicPayload;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.DeletePlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.PatchPlaybookSectionTopicInput;
import digital.windmill.audra.graphql.type.input.ReorderPlaybookSectionTopicInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaybookTopicMutationResolver implements GraphQLMutationResolver {

    private PlaybookTopicFacade facade;

    public PlaybookSectionTopicPayload createPlaybookSectionTopic(CreatePlaybookSectionTopicInput input) {
        return PlaybookSectionTopicPayload.builder()
                .topic(facade.createPlaybookTopic(input))
                .build();
    }

    public PlaybookSectionTopicPayload patchPlaybookSectionTopic(PatchPlaybookSectionTopicInput input) {
        return PlaybookSectionTopicPayload.builder()
                .topic(facade.patchPlaybookTopic(input))
                .build();
    }

    public DeletePlaybookSectionTopicPayload deletePlaybookSectionTopic(DeletePlaybookSectionTopicInput input) {
        return DeletePlaybookSectionTopicPayload.builder()
                .topic(facade.deletePlaybookTopic(input)).
                build();
    }

    public PlaybookSectionPayload reorderPlaybookSectionTopic(ReorderPlaybookSectionTopicInput input) {
        return PlaybookSectionPayload.builder()
                .section(facade.reorderTopic(input)).
                build();
    }
}
