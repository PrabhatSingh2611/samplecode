package digital.windmill.audra.graphql.type.input;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateLocationInput {
    private UUID uuid;
    private String name;
}
