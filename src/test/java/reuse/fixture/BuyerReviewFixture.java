package reuse.fixture;

import reuse.domain.BuyerReview;
import reuse.dto.review.buyer.CreateBuyerReviewRequestView;
import reuse.dto.review.buyer.FindBuyerReviewRequestView;
import reuse.dto.review.buyer.ListBuyerReviewResponseView;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.UserFixture.FIND_BY_EMAIL_RESPONSE_VIEW;
import static reuse.fixture.UserFixture.TEST_USER;

public class BuyerReviewFixture extends CommonFixture {
    public static final String TEST_CONTENT = "리뷰입니다.";
    public static final String TEST_SECOND_CONTENT = "두번째 리뷰입니다.";

    public static final Integer TEST_SCORE = 10;
    public static final Integer TEST_SECOND_SCORE = 5;

    public static final String TEST_TITLE = "제목입니다.";
    public static final String TEST_SECOND_TITLE = "두번째 제목입니다.";

    public static final CreateBuyerReviewRequestView CREATE_BUYER_REVIEW_REQUEST_VIEW = CreateBuyerReviewRequestView.builder()
            .content(TEST_CONTENT).score(TEST_SCORE).title(TEST_TITLE).boardId(DEFAULT_ID).build();

    public static final BuyerReview TEST_BUYER_REVIEW = BuyerReview.builder()
            .id(DEFAULT_ID).content(TEST_CONTENT).score(TEST_SCORE).title(TEST_TITLE).buyer(TEST_USER).build();

    public static final BuyerReview TEST_SECOND_BUYER_REVIEW = BuyerReview.TestBuilder()
            .content(TEST_SECOND_CONTENT).score(TEST_SECOND_SCORE).title(TEST_SECOND_TITLE).buyer(TEST_USER).build();

    public static final FindBuyerReviewRequestView FIND_BUYER_REVIEW_REQUEST_VIEW = FindBuyerReviewRequestView.builder()
            .buyer(FIND_BY_EMAIL_RESPONSE_VIEW).board(TEST_BOARD).content(TEST_CONTENT).score(TEST_SCORE).title(TEST_TITLE).build();

    public static final FindBuyerReviewRequestView FIND_SECOND_BUYER_REVIEW_REQUEST_VIEW = FindBuyerReviewRequestView.builder()
            .buyer(FIND_BY_EMAIL_RESPONSE_VIEW).board(TEST_BOARD).content(TEST_CONTENT).score(TEST_SCORE).title(TEST_TITLE).build();

    public static final ListBuyerReviewResponseView LIST_BUYER_REVIEW_REQUEST_VIEW = ListBuyerReviewResponseView.builder()
            .buyerReview(Arrays.asList(FIND_BUYER_REVIEW_REQUEST_VIEW, FIND_SECOND_BUYER_REVIEW_REQUEST_VIEW)).build();

    public static final List<BuyerReview> TEST_BUYER_REVIEWS = Arrays.asList(TEST_BUYER_REVIEW, TEST_BUYER_REVIEW, TEST_BUYER_REVIEW);

    public static CreateBuyerReviewRequestView getCreateBuyerReviewRequestView(Long boardId) {
        return CreateBuyerReviewRequestView.builder()
                .boardId(boardId)
                .content(TEST_CONTENT)
                .score(TEST_SCORE)
                .title(TEST_TITLE)
                .buyer(TEST_USER)
                .build();
    }
}
