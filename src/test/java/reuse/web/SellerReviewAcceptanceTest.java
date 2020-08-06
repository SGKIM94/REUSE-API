package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.SellerReview;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.FindBoardResponseView;
import reuse.dto.board.ModifyBoardStatusRequestView;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;
import static reuse.fixture.SellerReviewFixture.getCreateSellerReviewRequestView;
import static reuse.web.BoardAcceptanceTest.BOARD_BASE_URL;

public class SellerReviewAcceptanceTest extends AbstractAcceptanceTest {
    public static final String SELLER_REVIEW_BASE_URL = "/review/seller";

    private CreateWebClientTest createWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private String socialTokenId;

    @BeforeEach
    void setUp() {
        this.createWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
        socialTokenId = createWebClientTest.createUser();
    }

    @DisplayName("판매자가 구매자에 대한 후기를 남길 수 있는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void createSellerReview() {
        //given
        CreateBoardResponseView board = createWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, getJwt());
        Long boardId = board.getId();

        createWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/complete", new ModifyBoardStatusRequestView(boardId), Void.class, getJwt());


        // TODO: 게시판 상태를 COMPLETE 로 변경하는 부분이 필요
        //when
        EntityExchangeResult<SellerReview> expectResponse
                = createWebClientTest.postMethodWithAuthAcceptance
                (SELLER_REVIEW_BASE_URL, getCreateSellerReviewRequestView(boardId), SellerReview.class, getJwt());


        //then
        FindBoardResponseView foundBoard = findBoardById(boardId);

        HttpStatus status = expectResponse.getStatus();

        assertThat(status).isEqualTo(HttpStatus.OK);
        assertThat(foundBoard.getBuyerReview()).isNotNull();
    }

    public FindBoardResponseView findBoardById(Long boardId) {
        return createWebClientTest.getMethodWithAuthAcceptance(BOARD_BASE_URL + "/" + boardId, FindBoardResponseView.class, getJwt())
                .getResponseBody();
    }

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
    }
}
