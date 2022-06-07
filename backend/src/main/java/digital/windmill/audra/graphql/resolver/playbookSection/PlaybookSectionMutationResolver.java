package digital.windmill.audra.graphql.resolver.playbookSection;

import digital.windmill.audra.graphql.facade.PlaybookSectionFacade;
import digital.windmill.audra.graphql.type.DeletePlaybookSectionInput;
import digital.windmill.audra.graphql.type.DeletePlaybookSectionPayload;
import digital.windmill.audra.graphql.type.PatchPlaybookSectionInput;
import digital.windmill.audra.graphql.type.PlaybookSectionPayload;
import digital.windmill.audra.graphql.type.input.CreatePlaybookSectionInput;
import digital.windmill.audra.graphql.type.ReorderPlaybookSectionInput;
import digital.windmill.audra.graphql.type.input.PlaybookPayload;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaybookSectionMutationResolver implements GraphQLMutationResolver {
    private PlaybookSectionFacade playbookSectionFacade;

    public PlaybookSectionPayload createPlaybookSection(@NonNull CreatePlaybookSectionInput input) {
        return PlaybookSectionPayload.builder()
                .section(playbookSectionFacade.createPlaybookSection(input))
                .build();
    }

    public PlaybookSectionPayload patchPlaybookSection(@NonNull PatchPlaybookSectionInput input) {
        return PlaybookSectionPayload.builder()
                .section(playbookSectionFacade.patchPlaybookSection(input))
                .build();
    }

    public DeletePlaybookSectionPayload deletePlaybookSection(@NonNull DeletePlaybookSectionInput input) {
        return DeletePlaybookSectionPayload.builder()
                .section(playbookSectionFacade.deletePlaybookSection(input))
                .build();
    }

    public PlaybookPayload reorderPlaybookSection(@NonNull ReorderPlaybookSectionInput input) {
        return PlaybookPayload.builder()
                .playbook(playbookSectionFacade.reorderPlaybookSection(input))
                .build();
    }
}
