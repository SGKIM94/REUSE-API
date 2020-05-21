package reuse.storage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductFixture.TEST_IMAGE1;
import static reuse.fixture.ProductFixture.TEST_IMAGE_FILE_NAME1;
import static reuse.service.ProductServiceTest.S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME;

@SpringBootTest
public class S3UploaderTest {
    @Autowired
    private S3Uploader s3Uploader;

    @Value("${cloud.aws.s3.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.s3.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.region.static}")
    private String region;

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
        String uploadFileName = s3Uploader.upload(file, S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME);

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

    @DisplayName("S3 에 존재하는 모든 파일을 가져오는지")
    @Test
    public void getFiles() {
        //given
        List<String> files = s3Uploader.getFiles(1L);
        String testFile = files.get(0);

        //when
        assertThat(testFile).isNotBlank();
    }

    @DisplayName("S3 가져온 key 를 Object URL 로 변환 가능한지")
    @Test
    public void makeUrlByFile() {
        //given
        String fileURL = s3Uploader.makeUrlByFile("products/1");

        //when
        assertThat(fileURL).isEqualTo("https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/1");
    }
}
