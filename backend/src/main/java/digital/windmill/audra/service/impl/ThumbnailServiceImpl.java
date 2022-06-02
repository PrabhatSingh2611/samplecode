package digital.windmill.audra.service.impl;

import digital.windmill.audra.service.ThumbnailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.io.File;

@Component
@Slf4j
public class ThumbnailServiceImpl implements ThumbnailService {

    @Override
    public File generateThumbnail(@NonNull Part part, int thumbnailHeight, int thumbnailWidth) {
        log.info("Generate thumbnail for file {}", part.getSubmittedFileName());
        // TODO
        return null;
    }

}
