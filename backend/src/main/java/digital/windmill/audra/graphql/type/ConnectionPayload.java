package digital.windmill.audra.graphql.type;

import java.util.List;

import lombok.Data;

@Data
public abstract class ConnectionPayload<T extends Node> {

    static final PageInfo EMPTY = new PageInfo(0, 0);

    private List<T> items;
    private PageInfo pageInfo;
    private Long totalItems;

}
