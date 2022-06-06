package digital.windmill.audra.feed.graphql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import digital.windmill.audra.feed.dao.entity.PostEntity;
import digital.windmill.audra.feed.graphql.type.Post;
import digital.windmill.audra.graphql.mapper.DateTimeMapper;
import digital.windmill.audra.graphql.mapper.EmployeeMapper;
import digital.windmill.audra.graphql.mapper.ResourceMapper;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, ResourceMapper.class, DateTimeMapper.class})
public interface PostMapper {

    @Mapping(target = "id", source = "uuid")
    Post map(PostEntity entity);
}
