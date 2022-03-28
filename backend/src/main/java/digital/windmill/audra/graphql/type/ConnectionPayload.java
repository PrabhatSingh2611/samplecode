package digital.windmill.audra.graphql.type;

import java.util.List;

import lombok.Data;

@Data
public abstract class ConnectionPayload<T extends Node> {

    private List<T> items;
    private PageInfo pageInfo;
    private Long totalItems;

}
