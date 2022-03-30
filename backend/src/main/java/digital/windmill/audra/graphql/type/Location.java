package digital.windmill.audra.graphql.type;

import lombok.Data;

import java.util.UUID;

@Data
public class Location implements Node {

    private Long id;
    private UUID uuid;
    private String name;

}
