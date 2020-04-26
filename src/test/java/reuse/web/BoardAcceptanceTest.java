package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.Board;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;
import static reuse.fixture.ProductFixture.CREATE_PRODUCT_REQUEST_DTO;

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
        restWebClientTest.createProduct(CREATE_PRODUCT_REQUEST_DTO);

        //when
        EntityExchangeResult<Board> expectResponse
                = restWebClientTest.postMethodAcceptance(BOARD_BASE_URL, CREATE_BOARD_REQUEST_VIEW, Board.class);

        //then
        HttpHeaders responseHeaders = expectResponse.getResponseHeaders();

        //then
        assertThat(responseHeaders.getLocation()).isNotNull();
    }
}
