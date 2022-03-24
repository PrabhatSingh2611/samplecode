package digital.windmill.audra.graphql.type;

import graphql.relay.DefaultConnection;
import graphql.relay.Edge;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom implementation for GraphQL interface:
 *  interface Connection {
 *      items: [Node!]!
 *      pageInfo: PageInfo!
 *      totalItems: Int!
 *  }
 */
public class ExtendedConnection<T> extends DefaultConnection<T> {

    private final Long totalItems;

    public ExtendedConnection(List<Edge<T>> edges, ExtendedPageInfo pageInfo, Long totalItems) {
        super(edges, pageInfo);
        this.totalItems = totalItems;
    }

    public List<T> getItems() {
        return super.getEdges().stream().map(Edge::getNode).collect(Collectors.toList());
    }

    @Override
    public ExtendedPageInfo getPageInfo() {
        return (ExtendedPageInfo) super.getPageInfo();
    }

    public Long getTotalItems() {
        return totalItems;
    }
}
