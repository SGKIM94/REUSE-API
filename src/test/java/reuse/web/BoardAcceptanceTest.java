package reuse.web;

import org.junit.jupiter.api.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.dto.board.*;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardAcceptanceTest extends AbstractAcceptanceTest {
    public static final String BOARD_BASE_URL = "/boards";

    private CreateWebClientTest restWebClientTest;
    private String jwt;

    @BeforeEach
    void setUp() {
        this.restWebClientTest = new CreateWebClientTest(this.webTestClient);

        jwt = TokenAuthenticationCreator.getJwt(restWebClientTest.createUser());
    }

    @DisplayName("게사판 추가가 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    @Order(1)
    public void createBoard() {
        //when
        EntityExchangeResult<CreateBoardResponseView> expectResponse
                = restWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL, CREATE_BOARD_REQUEST_VIEW, CreateBoardResponseView.class, jwt);

        //then
        CreateBoardResponseView responseBody = expectResponse.getResponseBody();

        //then
        assertThat(responseBody.getId()).isNotNull();
    }

    @DisplayName("게시판 리스트 조회가 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    @Order(2)
    public void listBoard() {
        restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, jwt);
        restWebClientTest.createBoard(CREATE_SECOND_BOARD_REQUEST_VIEW, jwt);

        //when
        EntityExchangeResult<ListBoardResponseView> expectResponse
                = restWebClientTest.getMethodWithAuthAcceptance
                (BOARD_BASE_URL, ListBoardResponseView.class, jwt);

        //then
        ListBoardResponseView boards = expectResponse.getResponseBody();

        //then
        assertThat(boards.getSize()).isGreaterThan(1);
    }

    @DisplayName("게시판 리스트가 카테고리별로 조회가 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql","/insert-products.sql", "/insert-users.sql","/insert-boards.sql"})
    @Order(3)
    public void listBoardByCategory() {
        //when
        EntityExchangeResult<ListBoardWithProductResponseView> expectResponse
                = restWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/category", LIST_BOARD_BY_CATEGORY_REQUEST_VIEW, ListBoardWithProductResponseView.class, jwt);

        //then
        ListBoardWithProductResponseView boards = expectResponse.getResponseBody();
        FindWithProductResponseView firstBoard = boards.getFirstIndex();
        FindWithProductResponseView secondBoard = boards.getSecondIndex();

        //then
        assertThat(firstBoard.getId()).isEqualTo(TEST_FIRST_BOARD_ID);
        assertThat(secondBoard.getId()).isEqualTo(TEST_SIXTH_BOARD_ID);
    }

    @DisplayName("게시글의 상태를 예약 중으로 변경이 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    @Order(4)
    public void reserve() {
        //given
        CreateBoardResponseView board = restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, jwt);
        ModifyBoardStatusRequestView request = ModifyBoardStatusRequestView.toDto(board.getId());

        //when
        EntityExchangeResult<Long> exchangeResponse = restWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/reservation", request, Long.class, jwt);

        HttpStatus status = exchangeResponse.getStatus();
        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
    }

    @DisplayName("게시글의 상태를 판매완료 상태로 변경이 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    @Order(5)
    public void complete() {
        //given
        CreateBoardResponseView board = restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, jwt);
        reserveBoard(new ModifyBoardStatusRequestView(board.getId()));
        ModifyBoardStatusRequestView request = ModifyBoardStatusRequestView.toDto(board.getId());

        //when
        EntityExchangeResult<Long> exchangeResponse = restWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/complete", request, Long.class, jwt);

        HttpStatus status = exchangeResponse.getStatus();
        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
    }

    @DisplayName("게시판 수정이 가능한지")
    @Test
    @Disabled
    // 고립 필요
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    @Order(6)
    public void modifyBoard() {
        CreateBoardResponseView board = restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, jwt);

        //when
        EntityExchangeResult<Long> exchangeResponse = restWebClientTest.putMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/" + board.getId(), MODIFY_BOARD_REQUEST_DTO, Long.class, jwt);

        HttpStatus status = exchangeResponse.getStatus();
        assertThat(status).isEqualByComparingTo(HttpStatus.OK);

        Long boardId = exchangeResponse.getResponseBody();

        assertThat(boardId).isNotNull();
    }

    @DisplayName("Board 가 삭제가 되는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    @Order(7)
    public void deleteBoard() {
        CreateBoardResponseView board = restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, jwt);

        //when
        FluxExchangeResult<Void> response = this.webTestClient.post().uri(BOARD_BASE_URL + "/" + board.getId())
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Void.class);

        HttpStatus status = response.getStatus();
        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
    }

    public void reserveBoard(ModifyBoardStatusRequestView board) {
        restWebClientTest.postMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/reservation", board, Void.class, jwt).getResponseBody();
    }
}
