package digital.windmill.audra.graphql.type;

import lombok.Data;

import java.util.UUID;

@Data
public class Resource {

    private UUID id;
    private String url;
    private String thumbnail;
    private String mimeType;

}
