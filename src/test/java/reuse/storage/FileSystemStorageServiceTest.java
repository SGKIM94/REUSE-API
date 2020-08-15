package reuse.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Path;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static reuse.fixture.ProductImagesFixture.TEST_IMAGE1;

@Disabled("파일 업로드 사용 S3 로 변경")
public class FileSystemStorageServiceTest {
    public static final String REUSE_LOCATION = "/Users/kimsang-gu/Workspace/sanggu/toy-project/reuse/reuse-api/";

    private FileSystemStorageService fileSystemStorageService;
    private StorageProperties storageProperties = new StorageProperties();

    @BeforeEach
    public void init() {
        storageProperties.setLocation(REUSE_LOCATION + Math.abs(new Random().nextLong()));
        fileSystemStorageService = new FileSystemStorageService(storageProperties);

        fileSystemStorageService.init();
    }

    @DisplayName("파일이 존재하지 않는 경우")
    @Test
    public void loadNonExistent () {
        //when
        Path file = fileSystemStorageService.load("foo.txt");

        //then
        assertThat(file).doesNotExist();
    }

    @Disabled("사용 안함")
    @DisplayName("파일을 저장하고 불러오는지")
    @Test
    public void saveAndLoad () {
        //when
        fileSystemStorageService.store(TEST_IMAGE1);

        //then
        assertThat(fileSystemStorageService.load("foo.txt")).exists();
    }

    @DisplayName("../ 의 경로를 포함된 외부 파일의 접근인 경우 예외를 던지는지 - 경로를 이용한 directory attack 방지")
    @Test
    public void saveNotPermitted() {
        //when
        assertThrows(StorageException.class, () -> {
            fileSystemStorageService.store(new MockMultipartFile("foo", "../foo.txt",
                    MediaType.TEXT_PLAIN_VALUE, "this is save file test".getBytes()));
        });
    }

    @DisplayName("../ 를 중간에 포함한 경우에는 문제없이 저장 가능한지")
    @Test
    public void savePermitted() {
        //when
        fileSystemStorageService.store(new MockMultipartFile("foo", "bar/../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "this is save file test".getBytes()));
    }
}
