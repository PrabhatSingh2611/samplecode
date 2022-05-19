package digital.windmill.audra.graphql.resolver.announcement;

import digital.windmill.audra.graphql.facade.AnnouncementFacade;
import digital.windmill.audra.graphql.type.Announcement;
import digital.windmill.audra.graphql.type.AnnouncementPayload;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.input.AnnouncementInput;
import digital.windmill.audra.graphql.type.input.AnnouncementsInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AnnouncementResolver implements GraphQLQueryResolver {
    private AnnouncementFacade announcementFacade;

    public AnnouncementPayload announcement(AnnouncementInput input) {
        return AnnouncementPayload.builder()
                .item(announcementFacade.findAnnouncementByUuid(input.getId()))
                .build();
    }

    public ConnectionPayload<Announcement> announcements(AnnouncementsInput input) {
        return ConnectionUtils.buildPayload(announcementFacade.getAnnouncements(input));
    }
}
