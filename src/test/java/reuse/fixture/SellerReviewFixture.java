package reuse.fixture;

import reuse.dto.review.seller.CreateSellerReviewRequestView;

import static reuse.fixture.UserFixture.TEST_USER;

public class SellerReviewFixture extends CommonFixture {
    public static final String TEST_CONTENT = "판매자의 리뷰입니다.";
    public static final String TEST_RATING = "10";
    public static final String TEST_TITLE = "제목입니다.";

    public static CreateSellerReviewRequestView getCreateSellerReviewRequestView(Long boardId) {
        return CreateSellerReviewRequestView.builder()
                .boardId(boardId)
                .content(TEST_CONTENT)
                .rating(TEST_RATING)
                .title(TEST_TITLE)
                .seller(TEST_USER)
                .build();
    }
}
