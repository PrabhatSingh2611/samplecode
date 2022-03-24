package digital.windmill.audra.graphql.utils;

import digital.windmill.audra.graphql.type.ExtendedConnection;
import digital.windmill.audra.graphql.type.ExtendedPageInfo;
import digital.windmill.audra.graphql.type.Node;
import graphql.relay.*;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class ConnectionUtils {

    public static <T extends Node> ExtendedConnection<T> buildNodeConnection(Page<T> elements) {
        return buildConnection(elements, node -> node.getUuid().toString());
    }

    private static <T> ExtendedConnection<T> buildConnection(Page<T> page, Function<T, String> idExtractor) {
        if (page == null || page.isEmpty()) {
            return null;
        }
        
        List<Edge<T>> edges = page.getContent().stream()
                .map(e -> ConnectionUtils.createEdge(e, idExtractor))
                .collect(Collectors.toList());

        return new ExtendedConnection<>(edges, createPageInfo(page, edges), page.getTotalElements());
    }

    private static <T> ExtendedPageInfo createPageInfo(Page<T> page, List<Edge<T>> edges) {
        if (!CollectionUtils.isEmpty(edges)) {
            ConnectionCursor startCursor = CollectionUtils.firstElement(edges).getCursor();
            ConnectionCursor endCursor = CollectionUtils.lastElement(edges).getCursor();
            return new ExtendedPageInfo(startCursor, endCursor, page.getNumber(), page.getTotalPages());
        }
        return new ExtendedPageInfo();
    }

    private static <T> Edge<T> createEdge(T element, Function<T, String> idExtractor) {
        return new DefaultEdge<>(element, createCursor(element, idExtractor));
    }

    private static <T> ConnectionCursor createCursor(T element, Function<T, String> idExtractor) {
        return new DefaultConnectionCursor(idExtractor.apply(element));
    }
}
