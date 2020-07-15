package reuse.service;

import reuse.domain.SellerReview;
import reuse.domain.User;
import reuse.dto.review.seller.CreateSellerReviewRequestView;
import reuse.repository.SellerReviewRepository;

public class SellerReviewService {
    private SellerReviewRepository sellerReviewRepository;

    public SellerReview create(CreateSellerReviewRequestView createBuyerReviewRequestView, User testUser) {
        return new SellerReview();
    }
}
