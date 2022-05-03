package digital.windmill.audra.graphql.utils;

import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Node;
import digital.windmill.audra.graphql.type.PageInfo;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.List;

@UtilityClass
public class ConnectionUtils {

    public static <T extends Node> ConnectionPayload<T> buildPayload(Page<T> elements) {
        return new ConnectionPayload<>() {
            @Override
            public List<T> getItems() {
                return elements.getContent();
            }

            @Override
            public PageInfo getPageInfo() {
                return new PageInfo(elements.getNumber(), elements.getTotalPages());
            }

            @Override
            public Long getTotalItems() {
                return elements.getTotalElements();
            }
        };
    }

}
