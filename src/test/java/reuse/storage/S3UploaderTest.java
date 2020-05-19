package reuse.storage;

import com.amazonaws.services.s3.AmazonS3Client;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductFixture.TEST_IMAGE1;
import static reuse.fixture.ProductFixture.TEST_IMAGE_FILE_NAME1;

public class S3UploaderTest {
    private S3Uploader s3Uploader;
    private AmazonS3Client amazonS3Client;

    @BeforeEach
    public void setup() {

        amazonS3Client = new AmazonS3Client();
        this.s3Uploader = new S3Uploader(amazonS3Client);
    }

    @DisplayName("MultipartFile 을 File 타입으로 변경하는지")
    @Test
    public void convert() throws IOException {
        //when
        File convertedFIle = s3Uploader.convert(TEST_IMAGE1);
        File file = convertedFIle;

        //then
        assertThat(file).isFile();
    }

    @DisplayName("MultipartFile 을 S3에 업로드하는지")
    @Test
    public void uploadByMultipartFile() throws IOException {
        //when
        String uploadFileName = s3Uploader.upload(TEST_IMAGE1, TEST_IMAGE_FILE_NAME1);

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
