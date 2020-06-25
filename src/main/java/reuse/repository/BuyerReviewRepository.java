package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.BuyerReview;

public interface BuyerReviewRepository extends JpaRepository<BuyerReview, Long>, BuyerReviewRepositoryCustom {
}
