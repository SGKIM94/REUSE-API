package reuse.storage;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductFixture.TEST_IMAGE1;
import static reuse.fixture.ProductFixture.TEST_IMAGE_FILE_NAME1;

@ExtendWith(SpringExtension.class)
public class S3UploaderTest {
    private S3Uploader s3Uploader;

    @Value("${cloud.aws.s3.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.s3.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.region.static}")
    private String region;

    @BeforeEach
    public void setup() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();

        this.s3Uploader = new S3Uploader(amazonS3);
    }

    @DisplayName("MultipartFile 을 File 타입으로 변경하는지")
    @Test
    public void convert() throws IOException {
        //when
        File file = s3Uploader.convert(TEST_IMAGE1);

        //then
        assertThat(file).isFile();
    }

    @DisplayName("MultipartFile 을 S3에 업로드하는지")
    @Test
    public void uploadByMultipartFile() {
        //when
        String uploadFileName = s3Uploader.upload(TEST_IMAGE1, "products/1");

        //then
        assertThat(uploadFileName).isNotBlank();
    }

    @DisplayName("File 을 S3에 업로드하는지")
    @Test
    public void uploadByFile() throws IOException {
        //given
        File file = s3Uploader.convert(TEST_IMAGE1);

        //when
        String uploadFileName = s3Uploader.upload(file, TEST_IMAGE_FILE_NAME1);

        //then
        assertThat(uploadFileName).isNotBlank();
    }


    @DisplayName("이미지를 S3 에 넣을 수 있는지")
    @Test
    public void putImageToS3() throws IOException {
        //given
        File file = s3Uploader.convert(TEST_IMAGE1);

        //when
        String uploadFileName = s3Uploader.putImageToS3(file, TEST_IMAGE_FILE_NAME1);

        //then
        assertThat(uploadFileName).isNotBlank();
    }

    @DisplayName("기존 S3 에 있는 file 을 제거하는지")
    @Test
    public void removeNewFile() throws IOException {
        //given
        File file = s3Uploader.convert(TEST_IMAGE1);

        //when
        s3Uploader.removeNewFile(file);
    }

}
