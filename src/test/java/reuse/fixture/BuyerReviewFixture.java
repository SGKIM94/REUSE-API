package reuse.fixture;

import reuse.dto.review.buyer.CreateBuyerReviewRequestView;

public class BuyerReviewFixture extends CommonFixture {
    public static final String TEST_CONTENT = "리뷰입니다.";
    public static final String TEST_RATING = "10";
    public static final String TEST_TITLE = "제목입니다.";

    public static final CreateBuyerReviewRequestView CREATE_BUYER_REVIEW_REQUEST_VIEW = CreateBuyerReviewRequestView.builder()
            .content(TEST_CONTENT).rating(TEST_RATING).title(TEST_TITLE).boardId(DEFAULT_ID).build();
}
