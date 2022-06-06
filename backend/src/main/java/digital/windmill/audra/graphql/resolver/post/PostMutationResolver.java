package digital.windmill.audra.graphql.resolver.post;

import org.springframework.stereotype.Component;

import digital.windmill.audra.feed.graphql.PostFacade;
import digital.windmill.audra.feed.graphql.type.CreatePostInput;
import digital.windmill.audra.feed.graphql.type.PostPayload;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PostMutationResolver implements GraphQLMutationResolver {
    
    private final PostFacade facade;
    
    public PostPayload createPost(CreatePostInput input) {
        return new PostPayload(facade.createPost(input));
    }
}
