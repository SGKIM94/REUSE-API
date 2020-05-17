package reuse.storage;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    public String getFileUrl(Long prudctId) {
        return "https://" + bucket + "s3.amazonaws.com/products/" + prudctId.toString();
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

    public String upload(MultipartFile image, String directoryName) {
        try {
            File uploadFile = convert(image).orElseThrow(() -> new StorageException("file is invalid"));
            return upload(uploadFile, directoryName);
        } catch (IOException e) {
            throw new StorageException("file file upload!");
        }
    }

    public String upload(File image, String directoryName) {
        String fileName = directoryName + "/" + image.getName();
        String uploadImageUrl = putImageToS3(image, fileName);
        removeNewFile(image);
        return uploadImageUrl;
    }

    public String putImageToS3(File image, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, image)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("file is delete!");
            return;
        }

        log.info("cant file delete");
    }
}
