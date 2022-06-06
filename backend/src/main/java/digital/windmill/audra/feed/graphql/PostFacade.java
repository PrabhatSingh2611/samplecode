package digital.windmill.audra.feed.graphql;

import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.exception.AccessDeniedException;
import digital.windmill.audra.exception.InvalidInputDataException;
import digital.windmill.audra.feed.dao.PostService;
import digital.windmill.audra.feed.dao.entity.PostEntity;
import digital.windmill.audra.feed.graphql.mapper.PostMapper;
import digital.windmill.audra.feed.graphql.type.CreatePostInput;
import digital.windmill.audra.feed.graphql.type.Post;
import digital.windmill.audra.graphql.type.input.NodesInput;
import digital.windmill.audra.service.EmployeeService;
import digital.windmill.audra.service.ResourceService;
import digital.windmill.audra.service.impl.SecurityService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PostFacade {
    
    private final ResourceService resourceService;
    private final SecurityService securityService;
    private final EmployeeService employeeService;
    private final PostService service;
    private final PostMapper mapper;

    @Transactional
    public Post createPost(CreatePostInput input) {
        
        if (StringUtils.isBlank(input.getText())
                && ( input.getImages() == null
                    || CollectionUtils.isEmpty(input.getImages().getIds()))) {
            throw new InvalidInputDataException("Post doesn't allow empty text and no images at the same time.");
        }
        EmployeeEntity author = Optional.ofNullable(securityService.getCurrentUserUuid())
                .map(employeeService::findEmployeeByUuid)
                .orElseThrow(() -> new AccessDeniedException("Operation not supported from anonymous user"));
        PostEntity postEntity = service.createPost(input.getText(), author); 
        Optional.of(input)
            .map(CreatePostInput::getImages)
            .map(NodesInput::getIds)
            .map(resourceService::findByUuids)
            .ifPresent(postEntity::setImages);
        return mapper.map(postEntity);
    }

}
