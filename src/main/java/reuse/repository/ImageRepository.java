package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
