package digital.windmill.audra.service.impl;

import digital.windmill.audra.service.ThumbnailService;
import io.github.makbn.thumbnailer.AppSettings;
import io.github.makbn.thumbnailer.Thumbnailer;
import io.github.makbn.thumbnailer.ThumbnailerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Component
@Slf4j
public class ThumbnailServiceImpl implements ThumbnailService {

    @Override
    public File generateThumbnail(@NonNull Part part, int thumbnailHeight, int thumbnailWidth) {
        log.debug("Generate thumbnail for file {}", part.getSubmittedFileName());
        try {
            var sourceFile = Files.createTempFile(UUID.randomUUID().toString(), ".file").toFile();
            FileUtils.copyToFile(part.getInputStream(), sourceFile);
            return generateThumbnail(sourceFile, thumbnailHeight, thumbnailWidth);
        } catch (IOException | ThumbnailerException e) {
            log.warn("Can not generate thumbnail for file {} ", part.getSubmittedFileName(), e);
            return null;
        }
    }

    private File generateThumbnail(@NonNull File originalFile, int thumbnailHeight, int thumbnailWidth) throws IOException, ThumbnailerException {
        AppSettings.init(new String[]{
                "--temp_dir='" + System.getProperty("java.io.tmpdir") + "'",
                "--thumb_height=" + thumbnailHeight,
                "--thumb_width=" + thumbnailWidth,
        });
        var thumbnailFile = Files.createTempFile(UUID.randomUUID().toString(), ".jpg").toFile();
        Thumbnailer.createThumbnail(originalFile, thumbnailFile);
        return thumbnailFile;
    }
}
