package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.Board;
import reuse.dto.board.ListBoardResponseView;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.*;
import static reuse.fixture.ProductFixture.getCreateProductMap;

public class BoardAcceptanceTest extends AbstractAcceptanceTest {
    public static final String BOARD_BASE_URL = "/boards";

    private CreateWebClientTest restWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.restWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
    }

    @DisplayName("게사판 추가가 가능한지")
    @Test
    public void createBoard() {
        // product 를 주입하는 logic 필요
        restWebClientTest.createProduct(getCreateProductMap());

        //when
        EntityExchangeResult<Board> expectResponse
                = restWebClientTest.postMethodAcceptance(BOARD_BASE_URL, CREATE_BOARD_REQUEST_VIEW, Board.class);

        //then
        HttpHeaders responseHeaders = expectResponse.getResponseHeaders();

        //then
        assertThat(responseHeaders.getLocation()).isNotNull();
    }

    @Disabled
    @DisplayName("게시판 리스트 조회가 가능한지")
    @Test
    public void listBoard() {
        restWebClientTest.createProduct(getCreateProductMap());
        restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW);
        restWebClientTest.createBoard(CREATE_SECOND_BOARD_REQUEST_VIEW);

        //when
        EntityExchangeResult<ListBoardResponseView> expectResponse
                = restWebClientTest.getMethodAcceptance(BOARD_BASE_URL, ListBoardResponseView.class);

        //then
        ListBoardResponseView boards = expectResponse.getResponseBody();

        //then
        assertThat(boards.getSize()).isGreaterThan(1);
    }

    @Disabled
    @DisplayName("게시판 수정이 가능한지")
    @Test
    public void updateBoard() {
        restWebClientTest.createProduct(getCreateProductMap());
        restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW);

        //when
        EntityExchangeResult<ListBoardResponseView> expectResponse
                = restWebClientTest.postMethodAcceptance(BOARD_BASE_URL + "/2", MODIFY_BOARD_REQUEST_DTO, ListBoardResponseView.class);
    }

    @Disabled
    @DisplayName("Board 가 삭제가 되는지")
    @Test
    public void deleteBoard() {
        restWebClientTest.createProduct(getCreateProductMap());
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

}
