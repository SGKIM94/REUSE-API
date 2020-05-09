package reuse.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemStorageService {
    private final Path rootLocation;

    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public Path load(String s) {
        return rootLocation.resolve(s);
    }
}
