package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.SellerReview;
import reuse.domain.User;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.FindBoardResponseView;
import reuse.dto.board.ModifyBoardStatusRequestView;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;
import static reuse.fixture.SellerReviewFixture.getCreateSellerReviewRequestView;
import static reuse.fixture.UserFixture.*;
import static reuse.web.BoardAcceptanceTest.BOARD_BASE_URL;
import static reuse.web.TokenAuthenticationCreator.getJwt;

public class SellerReviewAcceptanceTest extends AbstractAcceptanceTest {
    public static final String SELLER_REVIEW_BASE_URL = "/review/seller";
    public static final String RESERVATION_ENDPOINT = "/reservation";
    public static final String COMPLETE_ENDPOINT = "/complete";

    private CreateWebClientTest createWebClientTest;
    private String jwt;

    @BeforeEach
    void setUp() {
        this.createWebClientTest = new CreateWebClientTest(this.webTestClient);

        jwt = getJwt(createWebClientTest.createUser());
    }

    @Disabled
    // 동시성에 대한 문제로 인해 우선 disabled 처리
    @DisplayName("판매자가 구매자에 대한 후기를 남길 수 있는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void createSellerReview() {
        //given
        User buyer = createWebClientTest.createUser(getCreateUserRequestView(TEST_SECOND_USER));

        CreateBoardResponseView board = createWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, jwt);
        Long boardId = board.getId();

        // 구매자가 예약 신청을 하면 판매자가 예약 확정을 해야되는 것인가?
        modifyBoardStatus(boardId, RESERVATION_ENDPOINT, getJwt(buyer));
        modifyBoardStatus(boardId, COMPLETE_ENDPOINT, jwt);

        //when
        EntityExchangeResult<SellerReview> expectResponse
                = createWebClientTest.postMethodWithAuthAcceptance
                (SELLER_REVIEW_BASE_URL, getCreateSellerReviewRequestView(boardId, TEST_USER),
                        SellerReview.class, jwt);

        //then
        FindBoardResponseView foundBoard = findBoardById(boardId);

        HttpStatus status = expectResponse.getStatus();

        assertThat(status).isEqualTo(HttpStatus.OK);
        assertThat(foundBoard.getSellerReview()).isNotNull();
    }

    private void modifyBoardStatus(Long boardId, String endPoint, String jwt) {
        createWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL + endPoint, new ModifyBoardStatusRequestView(boardId), Void.class, jwt);
    }

    public FindBoardResponseView findBoardById(Long boardId) {
        return createWebClientTest
                .getMethodWithAuthAcceptance
                        (BOARD_BASE_URL + "/" + boardId, FindBoardResponseView.class, jwt)
                .getResponseBody();
    }
}
