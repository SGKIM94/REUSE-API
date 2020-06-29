package reuse.fixture;

import reuse.domain.BuyerReview;
import reuse.dto.review.buyer.CreateBuyerReviewRequestView;
import reuse.dto.review.buyer.FindBuyerReviewRequestView;
import reuse.dto.review.buyer.ListBuyerReviewRequestView;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.UserFixture.TEST_USER;

public class BuyerReviewFixture extends CommonFixture {
    public static final String TEST_CONTENT = "리뷰입니다.";
    public static final String TEST_RATING = "10";
    public static final String TEST_TITLE = "제목입니다.";

    public static final CreateBuyerReviewRequestView CREATE_BUYER_REVIEW_REQUEST_VIEW = CreateBuyerReviewRequestView.builder()
            .content(TEST_CONTENT).rating(TEST_RATING).title(TEST_TITLE).boardId(DEFAULT_ID).build();

    public static final BuyerReview TEST_BUYER_REVIEW = BuyerReview.builder()
            .content(TEST_CONTENT).rating(TEST_RATING).title(TEST_TITLE).buyer(TEST_USER).build();

    public static final FindBuyerReviewRequestView FIND_BUYER_REVIEW_REQUEST_VIEW = FindBuyerReviewRequestView.builder()
            .buyer(TEST_USER).board(TEST_BOARD).content(TEST_CONTENT).rating(TEST_RATING).title(TEST_TITLE).build();

    public static final FindBuyerReviewRequestView FIND_SECOND_BUYER_REVIEW_REQUEST_VIEW = FindBuyerReviewRequestView.builder()
            .buyer(TEST_USER).board(TEST_BOARD).content(TEST_CONTENT).rating(TEST_RATING).title(TEST_TITLE).build();

    public static final ListBuyerReviewRequestView LIST_BUYER_REVIEW_REQUEST_VIEW = ListBuyerReviewRequestView.builder()
            .buyerReview(Arrays.asList(FIND_BUYER_REVIEW_REQUEST_VIEW, FIND_SECOND_BUYER_REVIEW_REQUEST_VIEW)).build();

    public static final List<BuyerReview> TEST_BUYER_REVIEWS = Arrays.asList(TEST_BUYER_REVIEW, TEST_BUYER_REVIEW);

    public static CreateBuyerReviewRequestView getCreateBuyerReviewRequestView(Long boardId) {
        return CreateBuyerReviewRequestView.builder()
                .boardId(boardId)
                .content(TEST_CONTENT)
                .rating(TEST_RATING)
                .title(TEST_TITLE)
                .build();
    }
}
