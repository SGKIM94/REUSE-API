package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
