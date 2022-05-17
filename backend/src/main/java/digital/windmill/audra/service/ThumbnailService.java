package digital.windmill.audra.service;

import org.springframework.lang.NonNull;

import javax.servlet.http.Part;
import java.io.File;

public interface ThumbnailService {
    File generateThumbnail(@NonNull Part part, int thumbnailHeight, int thumbnailWidth);
}
