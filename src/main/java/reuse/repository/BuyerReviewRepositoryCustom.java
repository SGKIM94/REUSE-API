package reuse.repository;

import reuse.domain.BuyerReview;

import java.util.List;

public interface BuyerReviewRepositoryCustom {
    List<BuyerReview> findBySeller(Long sellerId);
}
