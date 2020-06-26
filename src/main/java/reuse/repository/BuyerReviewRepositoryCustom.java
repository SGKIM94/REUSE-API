package reuse.repository;

import reuse.dto.review.buyer.ListBuyerReviewRequestView;

public interface BuyerReviewRepositoryCustom {
    ListBuyerReviewRequestView findBySeller(Long sellerId);
}
