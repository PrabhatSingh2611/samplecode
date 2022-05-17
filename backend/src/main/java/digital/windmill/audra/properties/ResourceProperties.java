package digital.windmill.audra.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "resource")
@Data
public class ResourceProperties {
    @Data
    public static class Thumbnail {
        private int height;
        private int width;
    }

    private Thumbnail thumbnail;
}
