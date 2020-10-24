package reuse.security;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import reuse.domain.User;
import reuse.exception.InvalidAccessTokenException;
import reuse.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static reuse.security.TokenAuthenticationService.BEARER_TOKEN_TYPE;

public class JwtAuthInterceptor extends HandlerInterceptorAdapter {
    private UserRepository userRepository;
    private TokenAuthenticationService tokenAuthenticationService;

    public static final String AUTH_USER_KEY = "user";

    public JwtAuthInterceptor(UserRepository userRepository, TokenAuthenticationService tokenAuthenticationService) {
        this.userRepository = userRepository;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null) {
            return true;
        }

        if (!tokenAuthenticationService.isVerifyToken(authorization)) {
            throw new InvalidAccessTokenException("Not invalid Token!");
        }

        String jwtWithoutType = authorization.replace(BEARER_TOKEN_TYPE + " ", "");

        String socialToken = tokenAuthenticationService.getSocialTokenByJwt(jwtWithoutType);

        User user = userRepository.findBySocialTokenId(socialToken);
        if (user == null) {
            throw new InvalidAccessTokenException("토큰에 사용자가 존재하지 않습니다.!");
        }

        request.setAttribute(AUTH_USER_KEY, user);

        return true;
    }
}
