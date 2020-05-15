package reuse.storage;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductFixture.TEST_IMAGE1;
import static reuse.fixture.ProductFixture.TEST_IMAGE_FILE_NAME1;

public class S3UploaderTest {
    private S3Uploader s3Uploader;

    @BeforeEach
    public void setup() {
        this.s3Uploader = new S3Uploader();
    }

    @DisplayName("MultipartFile 을 File 타입으로 변경하는지")
    @Test
    public void  convert() throws IOException {
        //when
        Optional<File> convertedFIle = s3Uploader.convert(TEST_IMAGE1);
        File file = convertedFIle.orElseThrow();

        //then
        assertThat(file).isFile();
    }

    @DisplayName("MultipartFile 을 S3에 업로드하는지")
    @Test
    public void  upload() {
        //when
        String uploadFileName = s3Uploader.upload(TEST_IMAGE1, TEST_IMAGE_FILE_NAME1);

        //then
        assertThat(uploadFileName).isNotBlank();
    }

    @DisplayName("이미지를 S3 에 넣을 수 있는지")
    @Test
    public void putImageToS3() {
        //when
        String uploadFileName = s3Uploader.putImageToS3(TEST_IMAGE1);

        //then
        assertThat(uploadFileName).isNotBlank();
    }

}
