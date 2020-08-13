package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.BuyerReview;
import reuse.domain.User;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.FindBoardResponseView;
import reuse.dto.review.buyer.ListBuyerReviewResponseView;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;
import static reuse.fixture.BuyerReviewFixture.TEST_BUYER_REVIEW;
import static reuse.fixture.BuyerReviewFixture.getCreateBuyerReviewRequestView;
import static reuse.fixture.CommonFixture.DEFAULT_ID;
import static reuse.web.BoardAcceptanceTest.BOARD_BASE_URL;

public class BuyerReviewAcceptanceTest extends AbstractAcceptanceTest {
    public static final String BUYER_REVIEW_BASE_URL = "/review/buyer";

    private CreateWebClientTest createWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private User loginUser;

    @BeforeEach
    void setUp() {
        this.createWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
        loginUser = createWebClientTest.createUser();
    }

    @DisplayName("구매자가 구매한 게시판에 구매평을 남기는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void createBuyerReview() {
        //given
        CreateBoardResponseView board = createWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, getJwt());
        Long boardId = board.getId();

        //when
        EntityExchangeResult<BuyerReview> expectResponse
                = createWebClientTest.postMethodWithAuthAcceptance
                (BUYER_REVIEW_BASE_URL, getCreateBuyerReviewRequestView(boardId), BuyerReview.class, getJwt());

        //then
        FindBoardResponseView foundBoard = findBoardById(boardId);

        HttpStatus status = expectResponse.getStatus();

        assertThat(status).isEqualTo(HttpStatus.OK);
        assertThat(foundBoard.getBuyerReview()).isNotNull();
    }

    @DisplayName("판매자에 연결된 모든 리뷰가 조회되는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void findBySeller() {
        //given
        CreateBoardResponseView board = createWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, getJwt());
        Long boardId = board.getId();
        createWebClientTest.createBuyerReview(boardId, getJwt());

        //when
        EntityExchangeResult<ListBuyerReviewResponseView> expectResponse
                = createWebClientTest.getMethodWithAuthAcceptance
                (BUYER_REVIEW_BASE_URL + "/" + DEFAULT_ID, ListBuyerReviewResponseView.class, getJwt());

        //then
        HttpStatus status = expectResponse.getStatus();
        ListBuyerReviewResponseView buyerReviews = expectResponse.getResponseBody();

        assertThat(status).isEqualTo(HttpStatus.OK);
        assertThat(buyerReviews.getSize()).isEqualTo(1);
    }

    @DisplayName("모든 구매후기를 가져오는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void list() {
        //given
        CreateBoardResponseView board = createWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, getJwt());
        Long boardId = board.getId();
        createWebClientTest.createBuyerReview(boardId, getJwt());

        //when
        EntityExchangeResult<ListBuyerReviewResponseView> expectResponse
                = createWebClientTest.getMethodWithAuthAcceptance
                (BUYER_REVIEW_BASE_URL, ListBuyerReviewResponseView.class, getJwt());

        //then
        HttpStatus status = expectResponse.getStatus();
        ListBuyerReviewResponseView buyerReviews = expectResponse.getResponseBody();

        assertThat(status).isEqualTo(HttpStatus.OK);
        assertThat(buyerReviews.getSize()).isEqualTo(1);
    }

    @DisplayName("구매후기를 수정 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void modify() {
        //given
        CreateBoardResponseView board = createWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, getJwt());
        Long boardId = board.getId();
        BuyerReview buyerReview = createWebClientTest.createBuyerReview(boardId, getJwt());

        //when
        EntityExchangeResult<Void> expectResponse
                = createWebClientTest.putMethodWithAuthAcceptance
                (BUYER_REVIEW_BASE_URL + "/" + buyerReview.getId(), TEST_BUYER_REVIEW, Void.class, getJwt());

        //then
        HttpStatus status = expectResponse.getStatus();

        assertThat(status).isEqualTo(HttpStatus.OK);
    }


    public FindBoardResponseView findBoardById(Long boardId) {
        return createWebClientTest.getMethodWithAuthAcceptance(BOARD_BASE_URL + "/" + boardId, FindBoardResponseView.class, getJwt())
                .getResponseBody();
    }

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(loginUser.getSocialTokenId());
    }
}
