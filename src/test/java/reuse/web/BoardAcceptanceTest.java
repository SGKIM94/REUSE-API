package reuse.web;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.Board;
import reuse.security.TokenAuthenticationService;

import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;

public class BoardAcceptanceTest extends AbstractAcceptanceTest {
    public static final String PRODUCT_BASE_URL = "/boards";

    private CreateWebClientTest restWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        cleanAllDatabases();
        this.restWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
    }

    @DisplayName("게사판 추가가 가능한지")
    @Test
    public void createBoard(SoftAssertions softly) {
        // product 를 주입하는 logic 필요
        //when
        EntityExchangeResult<Board> expectResponse
                = restWebClientTest.postMethodAcceptance(PRODUCT_BASE_URL, CREATE_BOARD_REQUEST_VIEW, Board.class);

        //then
        HttpHeaders responseHeaders = expectResponse.getResponseHeaders();

        //then
        softly.assertThat(responseHeaders.getLocation().toString()).isEqualTo("/boards/1");
    }
}
