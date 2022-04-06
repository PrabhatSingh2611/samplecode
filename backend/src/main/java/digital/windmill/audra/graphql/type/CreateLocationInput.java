package digital.windmill.audra.graphql.type;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateLocationInput {
    private UUID uuid;
    private String name;
}
