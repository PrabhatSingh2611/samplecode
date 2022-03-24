package digital.windmill.audra.graphql.type;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultPageInfo;

/**
 * Custom implementation for GraphQL type:
 *  type PageInfo {
 *     currentPage: Int!
 *     totalPages: Int!
 * }
 */
public class ExtendedPageInfo extends DefaultPageInfo {

    private final Integer currentPage;
    private final Integer totalPages;

    public ExtendedPageInfo() {
        super(null, null, false, false);
        this.currentPage = 0;
        this.totalPages = 0;
    }

    public ExtendedPageInfo(ConnectionCursor startCursor, ConnectionCursor endCursor,  Integer currentPage, Integer totalPages) {
        super(startCursor, endCursor, currentPage > 0, currentPage + 1 < totalPages);
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
