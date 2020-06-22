package reuse.chat.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.stomp.StompCommand;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static reuse.fixture.UserFixture.TEST_SOCIAL_TOKEN_ID;

public class StompHandlerTest {
    private StompHandler stompHandler;

    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        tokenAuthenticationService = new TokenAuthenticationService();
        stompHandler = new StompHandler(tokenAuthenticationService);
    }

    @DisplayName("Websocket 에서 Connect 를 맺을 때 AccessToken 이 올바를 때 참을 리턴하는지")
    @Test
    public void isInvalidAccessTokenWhenConnect() {
        //when
        boolean isValidAccessToken = stompHandler.isInvalidAccessTokenWhenConnect(StompCommand.CONNECT, getJwt());

        //then
        assertThat(isValidAccessToken).isTrue();
    }

    @DisplayName("Websocket 에서 Connect 를 맺을 때 AccessToken 이 올바르지 않을 때 거짓을 리턴하는지")
    @Test
    public void isNotInvalidAccessTokenWhenConnect() {
        //when
        boolean isValidAccessToken = stompHandler.isInvalidAccessTokenWhenConnect(StompCommand.CONNECT, "Invalid token");

        //then
        assertThat(isValidAccessToken).isFalse();
    }

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(TEST_SOCIAL_TOKEN_ID);
    }
}
