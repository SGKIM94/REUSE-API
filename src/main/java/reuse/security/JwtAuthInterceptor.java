package reuse.security;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import reuse.domain.User;
import reuse.repository.UserRepository;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthInterceptor extends HandlerInterceptorAdapter {
    private UserRepository userRepository;
    private TokenAuthenticationService tokenAuthenticationService;

    public static final String AUTH_USER_KEY = "user";

    public JwtAuthInterceptor(UserRepository userRepository, TokenAuthenticationService tokenAuthenticationService) {
        this.userRepository = userRepository;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null) {
            throw new AuthenticationException("Header 에 token 이 존재하지 않습니다.");
        }

        if (!tokenAuthenticationService.isVerifyToken(authorization)) {
            throw new AuthenticationException("Not invalid Token!");
        }

        String socialToken = tokenAuthenticationService.getSocialTokenByJwt(authorization);

        User user = userRepository.findBySocialTokenId(socialToken);
        request.setAttribute(AUTH_USER_KEY, user);

        return user != null;
    }
}
