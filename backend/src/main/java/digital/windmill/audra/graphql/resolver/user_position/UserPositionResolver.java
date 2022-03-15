package ch.windmill.audra.graphql.resolver.user_position;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ch.windmill.audra.graphql.facade.UserPositionFacade;
import ch.windmill.audra.graphql.type.UserPosition;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserPositionResolver implements GraphQLQueryResolver {

    private UserPositionFacade facade;

    public UserPosition userPosition(UUID id) {
        return facade.findById(id);
    }

}
