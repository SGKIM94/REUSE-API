package reuse.storage;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductFixture.TEST_IMAGE1;

public class S3UploaderTest {

    @DisplayName("MultipartFile 을 File 타입으로 변경하는지")
    @Test
    public void  convert() {
        //given
        Optional<File> convertedFIle = S3Uploader.convert(TEST_IMAGE1);
        File file = convertedFIle.orElseThrow();

        //when

        //then
        assertThat(file).isFile();
    }
}
