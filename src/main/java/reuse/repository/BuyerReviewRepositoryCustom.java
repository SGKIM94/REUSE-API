package reuse.repository;

import reuse.dto.review.buyer.ListBuyerReviewResponseView;

public interface BuyerReviewRepositoryCustom {
    ListBuyerReviewResponseView findBySeller(Long sellerId);
}
