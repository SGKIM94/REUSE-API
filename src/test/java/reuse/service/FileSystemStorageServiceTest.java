package reuse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reuse.storage.FileSystemStorageService;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class FileSystemStorageServiceTest {

    @BeforeEach
    public void init() {

    }

    @DisplayName("파일이 존재하지 않는 경우")
    @Test
    public void loadNonExistent () {
        //when
        Path file =  FileSystemStorageService.load("foo.txt");

        //then
        assertThat(file).doesNotExist();
    }
}
