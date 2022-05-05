package digital.windmill.audra.graphql.type;

import java.util.UUID;

import lombok.Data;

@Data
public class Resource {

    private UUID uuid;
    private String url;
    private String thumbnail;

}
