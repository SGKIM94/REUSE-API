package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
