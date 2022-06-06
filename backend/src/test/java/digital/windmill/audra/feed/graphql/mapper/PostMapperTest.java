package digital.windmill.audra.feed.graphql.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.feed.dao.entity.PostEntity;
import digital.windmill.audra.graphql.mapper.DateTimeMapperImpl;
import digital.windmill.audra.graphql.mapper.EmployeeMapperImpl;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapperImpl;
import digital.windmill.audra.graphql.mapper.LocationCountryMapperImpl;
import digital.windmill.audra.graphql.mapper.LocationMapperImpl;
import digital.windmill.audra.graphql.mapper.ResourceMapperImpl;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {
        PostMapperImpl.class,
        EmployeeMapperImpl.class, 
        EmployeePositionMapperImpl.class, 
        LocationMapperImpl.class, 
        LocationCountryMapperImpl.class, 
        ResourceMapperImpl.class,
        DateTimeMapperImpl.class
        })
class PostMapperTest {
    
    private static final UUID POST_UUID = UUID.randomUUID();
    private static final String TEXT = "post text";
    private static final UUID RESOURCE_UUID = UUID.randomUUID();
    private static final UUID AUTHOR_UUID = UUID.randomUUID();
    private static final ZonedDateTime CREATED_AT = ZonedDateTime.now()
            .withZoneSameInstant(ZoneId.of("UTC"));

    @Autowired
    private PostMapper mapper;

    @Test
    void shouldMap() {
        var result = mapper.map(createEntity());
        assertEquals(POST_UUID, result.getId());
        assertEquals(TEXT, result.getText());
        assertEquals(RESOURCE_UUID, result.getImages().get(0).getId());
        assertEquals(AUTHOR_UUID, result.getAuthor().getId());
        assertEquals(CREATED_AT, result.getCreatedAt());
    }

    private PostEntity createEntity() {
        PostEntity postEntity = new PostEntity();
        postEntity.setUuid(POST_UUID);
        postEntity.setText(TEXT);
        postEntity.setImages(List.of(createResource()));
        postEntity.setAuthor(createAuthor());
        postEntity.setCreatedAt(CREATED_AT.toInstant());
        return postEntity;
    }

    private ResourceEntity createResource() {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setUuid(RESOURCE_UUID);
        return resourceEntity;
    }

    private EmployeeEntity createAuthor() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setUuid(AUTHOR_UUID);
        return employeeEntity;
    }

}
