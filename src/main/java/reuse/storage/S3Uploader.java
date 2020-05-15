package reuse.storage;

import com.amazonaws.services.s3.AmazonS3Client;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload() {
    }

    public Optional<File> convert(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty() || StringUtils.isEmpty(image.getOriginalFilename())) {
            throw new StorageException("file is empty when convert file!");
        }

        File convertFile = new File(image.getOriginalFilename());

        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(image.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }
}
