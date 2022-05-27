package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.PlaybookVideoEntity;
import digital.windmill.audra.graphql.type.PlaybookVideo;
import digital.windmill.i18n.mapper.I18nMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {I18nMapper.class},
        imports = {UUID.class}
)
public interface PlaybookVideoMapper {

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "url", expression = "java(convertUrl(entity.getUrl()))")
    PlaybookVideo map(PlaybookVideoEntity entity);

    default URL convertUrl(String URL){
        if (Objects.isNull(URL)) {
            return null;
        }
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
