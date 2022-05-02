package digital.windmill.audra.storage;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorableObject {

    private InputStream stream;
    private String fileName;
    private String contentType;
    private Map<String, ? extends Serializable> metadata;

}
