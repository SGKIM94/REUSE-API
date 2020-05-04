package reuse.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.UserFixture.KIM_EMAIL;
import static reuse.security.TokenAuthenticationService.BEARER_TOKEN_TYPE;

public class TokenAuthenticationServiceTest {
    private static final String SALT = "63B75D39E3F6BFE72263F7C1145AC22E";
    private static final String EMAIL_KEY = "email";

    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
    }

    @DisplayName("JWT 를 위한 키가 생성되는지")
    @Test
    public void generateKey() {
        //given
        byte[] key = tokenAuthenticationService.generateKey(SALT);

        //then
        assertThat(key).isEqualTo(SALT.getBytes());
    }

    @DisplayName("Jwt Token 을 생성하는지")
    @Test
    public void toJwtByEmail() {
        //when
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(KIM_EMAIL);

        //then
        assertThat(jwt).isNotEmpty();
    }

    @Disabled
    @DisplayName("JWT 에서 Claim 을 가져오는지")
    @Test
    public void getJwtClaim() {
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(KIM_EMAIL);

        //when
        Jws<Claims> claims = tokenAuthenticationService.getJwtClaim(jwt);

        //then
        assertThat(claims.getBody().get(EMAIL_KEY)).isEqualTo(KIM_EMAIL);
    }

    @DisplayName("Claims 에서 email 을 가져오는지")
    @Test
    public void getEmailByClaim() {
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(KIM_EMAIL);
        Jws<Claims> claims = tokenAuthenticationService.getJwtClaim(jwt);

        //when
        String email = tokenAuthenticationService.getSocialTokenIdByClaims(claims);

        //then
        assertThat(email).isEqualTo(KIM_EMAIL);
    }

    @DisplayName("Claims 에서 tokenType 을 가져오는지")
    @Test
    public void getTokenType() {
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(KIM_EMAIL);

        //when
        String tokenType = tokenAuthenticationService.getTokenTypeByJwt(jwt);

        //then
        assertThat(tokenType).isEqualTo(BEARER_TOKEN_TYPE);
    }

    @DisplayName("jwt 가 검증된 토큰인지 확인")
    @Test
    public void isVerifyToken() {
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(KIM_EMAIL);

        //when
        boolean isVerify = tokenAuthenticationService.isVerifyToken(jwt);

        //then
        assertThat(isVerify).isTrue();
    }

    @DisplayName("jwt 로 email 을 가져오는지")
    @Test
    public void getEmailByJwt() {
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(KIM_EMAIL);

        //when
        String email = tokenAuthenticationService.getEmailByJwt(jwt);

        //then
        assertThat(email).isEqualTo(KIM_EMAIL);
    }

}

