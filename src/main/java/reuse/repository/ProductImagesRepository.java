package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.ProductImages;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {
}
