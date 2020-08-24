package reuse.web;

import reuse.domain.User;
import reuse.security.TokenAuthenticationService;

public class TokenAuthenticationCreator {
    public static String getJwt(User loginUser) {
        TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();
        return tokenAuthenticationService.toJwtBySocialTokenId(loginUser.getSocialTokenId());
    }
}
