package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.Board;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.FindWithProductResponseView;
import reuse.dto.board.ListBoardResponseView;
import reuse.dto.board.ListBoardWithProductResponseView;
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
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void createBoard() {
        //when
        EntityExchangeResult<CreateBoardResponseView> expectResponse
                = restWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL, CREATE_BOARD_REQUEST_VIEW, CreateBoardResponseView.class, getJwt());

        //then
        CreateBoardResponseView responseBody = expectResponse.getResponseBody();

        //then
        assertThat(responseBody.getId()).isNotNull();
    }

    @DisplayName("게시판 리스트 조회가 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void listBoard() {
        String jwt = getJwt();
        restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, jwt);
        restWebClientTest.createBoard(CREATE_SECOND_BOARD_REQUEST_VIEW, jwt);

        //when
        EntityExchangeResult<ListBoardResponseView> expectResponse
                = restWebClientTest.getMethodWithAuthAcceptance
                (BOARD_BASE_URL, ListBoardResponseView.class, getJwt());

        //then
        ListBoardResponseView boards = expectResponse.getResponseBody();

        //then
        assertThat(boards.getSize()).isGreaterThan(1);
    }

    @DisplayName("게시판 수정이 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void updateBoard() {
        CreateBoardResponseView board = restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, getJwt());

        //when
        EntityExchangeResult<Board> exchangeResponse = restWebClientTest.putMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/" + board.getId(), MODIFY_BOARD_REQUEST_DTO, Board.class, getJwt());

        HttpStatus status = exchangeResponse.getStatus();
        assertThat(status).isEqualByComparingTo(HttpStatus.OK);

        Board modifiedBoard = exchangeResponse.getResponseBody();

        assertThat(modifiedBoard.getTitle()).isEqualTo(TEST_MODIFY_BOARD_TITLE);
    }

    @DisplayName("Board 가 삭제가 되는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void deleteBoard() {
        CreateBoardResponseView board = restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, getJwt());

        //when
        FluxExchangeResult<Void> response = this.webTestClient.post().uri(BOARD_BASE_URL + "/" + board.getId())
                .header(HttpHeaders.AUTHORIZATION, getJwt())
                .exchange()
                .expectStatus().isOk()
                .returnResult(Void.class);

        HttpStatus status = response.getStatus();
        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
    }

    @DisplayName("게시판 리스트가 카테고리별로 조회가 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql",
            "/insert-products.sql", "/insert-boards.sql"})
    public void listBoardByCategory() {
        //when
        EntityExchangeResult<ListBoardWithProductResponseView> expectResponse
                = restWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/category", LIST_BOARD_BY_CATEGORY_REQUEST_VIEW, ListBoardWithProductResponseView.class, getJwt());

        //then
        ListBoardWithProductResponseView boards = expectResponse.getResponseBody();
        FindWithProductResponseView firstBoard = boards.getFirstIndex();
        FindWithProductResponseView secondBoard = boards.getSecondIndex();

        //then
        assertThat(firstBoard.getId()).isEqualTo(TEST_FIRST_BOARD_ID);
        assertThat(secondBoard.getId()).isEqualTo(TEST_SIXTH_BOARD_ID);
    }


    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
    }
}
