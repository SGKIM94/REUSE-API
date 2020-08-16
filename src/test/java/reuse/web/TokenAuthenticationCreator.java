package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import reuse.domain.User;
import reuse.security.TokenAuthenticationService;

public class TokenAuthenticationCreator {
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
    }

    public String getJwt(User loginUser) {
        return tokenAuthenticationService.toJwtBySocialTokenId(loginUser.getSocialTokenId());
    }
}
