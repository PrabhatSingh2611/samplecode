package digital.windmill.audra.graphql.resolver.playbookTopic;

import digital.windmill.audra.graphql.facade.impl.PlaybookTopicFacade;
import digital.windmill.audra.graphql.type.PlaybookSectionTopicPayload;
import digital.windmill.audra.graphql.type.input.PlaybookSectionTopicInput;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaybookTopicQueryResolver implements GraphQLQueryResolver {

    private PlaybookTopicFacade facade;

    public PlaybookSectionTopicPayload playbookSectionTopic(PlaybookSectionTopicInput input) {
        return PlaybookSectionTopicPayload.builder()
                .topic(facade.findTopicByUuid(input.getId()))
                .build();
    }
}
