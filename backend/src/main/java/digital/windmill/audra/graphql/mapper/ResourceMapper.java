package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.graphql.type.Resource;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public abstract class ResourceMapper {

    private static final String RESOURCE_SEPARATOR = "/";

    private String urlPath;

    @Autowired
    void setUrlPath(@Value("${storage.destination.url}") String urlPath) {
        if (!StringUtils.endsWith(urlPath, RESOURCE_SEPARATOR)) {
            urlPath += RESOURCE_SEPARATOR;
        }
        this.urlPath = urlPath;
    }

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "url", source = "entity", qualifiedByName = "buildUrl")
    @Mapping(target = "thumbnail", source = "thumbnail", qualifiedByName = "buildUrl")
    public abstract Resource map(ResourceEntity entity);

    @Named("buildUrl")
    public String buildUrl(ResourceEntity entity) {
        if (entity == null
                || entity.getUuid() == null) {
            return null;
        }
        return StringUtils.join(urlPath, entity.getUuid());
    }
}
