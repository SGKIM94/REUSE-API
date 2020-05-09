package reuse.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Path;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("파일을 저장하고 불러오는지")
    @Test
    public void saveAndLoad () {
        //when
        fileSystemStorageService.store(new MockMultipartFile("foo", "bar/../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "this is save file test".getBytes()));

        //then
        assertThat(fileSystemStorageService.load("foo.txt")).exists();
    }

}
