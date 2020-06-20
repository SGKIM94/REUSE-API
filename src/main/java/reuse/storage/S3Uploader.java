package reuse.storage;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@NoArgsConstructor
public class S3Uploader {
    private static final String REUSE_S3_BUCKET_NAME = "reuse-s3";

    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.s3.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.region.static}")
    private String region;

    @PostConstruct
    public void s3SetUp() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    public List<String> getFiles(Long productId) {
        ObjectListing files = amazonS3.listObjects(REUSE_S3_BUCKET_NAME, "products/" + productId + "/");

        return files.getObjectSummaries().stream()
            .map(file -> makeUrlByFile(file.getKey()))
            .collect(Collectors.toList());
    }

    public String makeUrlByFile(String fileKey) {
        return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + fileKey;
    }

    public File convert(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty() || StringUtils.isEmpty(image.getOriginalFilename())) {
            throw new StorageException("file is empty when convert file!");
        }

        File convertFile = new File(image.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(image.getBytes());
        }

        return convertFile;
    }

    public String upload(MultipartFile image, String directoryName) {
        try {
            return upload(convert(image), directoryName);
        } catch (IOException e) {
            throw new StorageException("file file upload!");
        }
    }

    public String upload(File image, String directoryName) {
        removeNewFile(image);

        return putImageToS3(image, directoryName + "/" + image.getName());
    }

    public String putImageToS3(File image, String fileName) {
        amazonS3.putObject(new PutObjectRequest(REUSE_S3_BUCKET_NAME, fileName, image)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3.getUrl(REUSE_S3_BUCKET_NAME, fileName).toString();
    }

    public void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("file is delete!");
            return;
        }

        log.info("cant file delete");
    }
}
