package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.ListBoardResponseView;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.*;

public class BoardAcceptanceTest extends AbstractAcceptanceTest {
    public static final String BOARD_BASE_URL = "/boards";

    private CreateWebClientTest restWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private String socialTokenId;

    @BeforeEach
    void setUp() {
        this.restWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
        socialTokenId = restWebClientTest.createUser();
    }

    @DisplayName("게사판 추가가 가능한지")
    @Test
    @Sql(scripts = {"/insert-products.sql"})
    public void createBoard() {
        //when
        EntityExchangeResult<CreateBoardResponseView> expectResponse
                = restWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL, CREATE_BOARD_REQUEST_VIEW, CreateBoardResponseView.class, getJwt(socialTokenId));

        //then
        CreateBoardResponseView responseBody = expectResponse.getResponseBody();

        //then
        assertThat(responseBody.getId()).isNotNull();
    }

    @Disabled
    @DisplayName("게시판 리스트 조회가 가능한지")
    @Test
    @Sql(scripts = {"/insert-products.sql"})
    public void listBoard() {
        restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW);
        restWebClientTest.createBoard(CREATE_SECOND_BOARD_REQUEST_VIEW);

        //when
        EntityExchangeResult<ListBoardResponseView> expectResponse
                = restWebClientTest.getMethodWithAuthAcceptance
                (BOARD_BASE_URL, ListBoardResponseView.class, getJwt(socialTokenId));

        //then
        ListBoardResponseView boards = expectResponse.getResponseBody();

        //then
        assertThat(boards.getSize()).isGreaterThan(1);
    }

    @Disabled
    @DisplayName("게시판 수정이 가능한지")
    @Test
    @Sql(scripts = {"/insert-products.sql"})
    public void updateBoard() {
        restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW);

        //when
        EntityExchangeResult<ListBoardResponseView> expectResponse
                = restWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/2", MODIFY_BOARD_REQUEST_DTO, ListBoardResponseView.class, getJwt(socialTokenId));
    }

    @Disabled
    @DisplayName("Board 가 삭제가 되는지")
    @Test
    @Sql(scripts = {"/insert-products.sql"})
    public void deleteBoard() {
        restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW);

        //when
        FluxExchangeResult<Void> response = this.webTestClient.get().uri(BOARD_BASE_URL + "/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(Void.class);

        HttpStatus status = response.getStatus();
        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
    }

    public String getJwt(String socialTokenId) {
        return tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
    }
}
