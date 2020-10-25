package reuse.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import reuse.domain.User;
import reuse.exception.InvalidAccessTokenException;
import reuse.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static reuse.fixture.UserFixture.TEST_USER;
import static reuse.fixture.UserFixture.TEST_USER_EMAIL;
import static reuse.security.JwtAuthInterceptor.AUTH_USER_KEY;

@SpringBootTest
public class JwtAuthInterceptorTest {
    private JwtAuthInterceptor jwtAuthInterceptor;
    private TokenAuthenticationService tokenAuthenticationService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        this.jwtAuthInterceptor = new JwtAuthInterceptor(userRepository, tokenAuthenticationService);
    }

    @DisplayName("사용자 로그인 시 토큰 검증을 진행하는지")
    @Test
    public void preHandle() {
        //given
        when(userRepository.findBySocialTokenId(TEST_USER_EMAIL)).thenReturn(TEST_USER);
        MockHttpServletRequest request = jwtAuthHttpRequest(TEST_USER_EMAIL);

        //when
        boolean isAuthorization = jwtAuthInterceptor.preHandle(request, null, null);

        //then
        assertThat(isAuthorization).isTrue();
        assertThat(request.getAttribute(AUTH_USER_KEY).getClass()).isEqualTo(User.class);
    }

    @DisplayName("사용자 로그인 시 토큰 검증 시 사용자가 없는 경우 예외처리하는지")
    @Test
    public void preHandleWithNotUser() {
        //given
        when(userRepository.findBySocialTokenId(TEST_USER_EMAIL)).thenReturn(null);
        MockHttpServletRequest request = jwtAuthHttpRequest(TEST_USER_EMAIL);

        //then
        assertThrows(InvalidAccessTokenException.class, () -> {
            jwtAuthInterceptor.preHandle(request, null, null);
        });
    }


    private MockHttpServletRequest jwtAuthHttpRequest(String email) {
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(email);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, jwt);
        return request;
    }
}
