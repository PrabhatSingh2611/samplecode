package digital.windmill.audra.graphql.resolver.announcement;

import digital.windmill.audra.graphql.facade.AnnouncementFacade;
import digital.windmill.audra.graphql.type.AnnouncementPayload;
import digital.windmill.audra.graphql.type.DeleteAnnouncementPayload;
import digital.windmill.audra.graphql.type.input.CreateAnnouncementInput;
import digital.windmill.audra.graphql.type.input.DeleteAnnouncementInput;
import digital.windmill.audra.graphql.type.input.UpdateAnnouncementInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AnnouncementMutationResolver implements GraphQLMutationResolver {

    private AnnouncementFacade announcementFacade;

    public AnnouncementPayload createAnnouncement(CreateAnnouncementInput input) {
        return AnnouncementPayload.builder()
                .item(announcementFacade.createAnnouncement(input))
                .build();
    }

    public AnnouncementPayload updateAnnouncement(UpdateAnnouncementInput input) {
        return AnnouncementPayload.builder()
                .item(announcementFacade.updateAnnouncement(input))
                .build();
    }

    public DeleteAnnouncementPayload deleteAnnouncement(DeleteAnnouncementInput input) {
        return DeleteAnnouncementPayload.builder()
                .announcement(announcementFacade.deleteAnnouncement(input)).
                build();
    }
}
