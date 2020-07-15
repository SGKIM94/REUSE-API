package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.SellerReview;

public interface SellerReviewRepository extends JpaRepository<SellerReview, Long> {
}
