package reuse.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

public class S3Uploader {

    public static Optional<File> convert(MultipartFile image) {
        File convertFile = new File(image.getOriginalFilename());

    }
}
