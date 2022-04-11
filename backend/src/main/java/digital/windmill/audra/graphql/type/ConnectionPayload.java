package digital.windmill.audra.graphql.type;

import lombok.Data;

import java.util.List;

@Data
public abstract class ConnectionPayload<T extends Node> {

    private List<T> items;
    private PageInfo pageInfo;
    private Long totalItems;

}
