package reuse.security;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import reuse.domain.User;
import reuse.repository.UserRepository;

import static org.mockito.Mockito.when;
import static reuse.fixture.UserFixture.KIM_EMAIL;
import static reuse.fixture.UserFixture.TEST_USER;
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

    @Disabled
    @DisplayName("사용자 로그인 시 토큰 검증을 진행하는지")
    @Test
    public void preHandle(SoftAssertions softly) throws Exception {
        //given
        when(userRepository.findBySocialTokenId(KIM_EMAIL)).thenReturn(TEST_USER);
        MockHttpServletRequest request = jwtAuthHttpRequest(KIM_EMAIL);

        //when
        boolean isAuthorization = jwtAuthInterceptor.preHandle(request, null, null);

        //then
        softly.assertThat(isAuthorization).isTrue();
        softly.assertThat(request.getAttribute(AUTH_USER_KEY).getClass()).isEqualTo(User.class);
    }

    private MockHttpServletRequest jwtAuthHttpRequest(String email) {
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(email);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, jwt);
        return request;
    }
}
