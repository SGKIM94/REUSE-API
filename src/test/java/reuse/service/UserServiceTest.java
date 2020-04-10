package reuse.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reuse.dto.user.CreateUserResponseView;
import reuse.dto.user.FindByEmailResponseView;
import reuse.dto.user.LoginUserResponseView;
import reuse.exception.ExistUserException;
import reuse.repository.UserRepository;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.UserFixture.*;
import static reuse.security.TokenAuthenticationService.BEARER_TOKEN_TYPE;

@SpringBootTest
public class UserServiceTest {
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        this.userService = new UserService(tokenAuthenticationService, userRepository);
    }

    @DisplayName("이미 회원이 있는 유저인 경우 예외를 처리하는지")
    @Test
    public void sighUpWhenExistUser() {
        when(userRepository.findByEmail(any())).thenReturn(FIND_BY_EMAIL_RESPONSE_VIEW);

        Assertions.assertThrows(ExistUserException.class, () -> {
            userService.singUp(USER_SIGH_UP_REQUEST_DTO);
        });
    }

    @DisplayName("회원이 정상적으로 등록되는지")
    @Test
    public void signUp() {
        when(userRepository.findByEmail(any())).thenReturn(FindByEmailResponseView.builder().build());
        when(userRepository.save(any())).thenReturn(TEST_USER);

        CreateUserResponseView user = userService.singUp(USER_SIGH_UP_REQUEST_DTO);

        assertThat(user.getId()).isNotNull();
    }

    @DisplayName("회원이 로그인이 성공하여 토큰을 리턴하는지")
    @Test
    public void login(SoftAssertions softly) {
        when(userRepository.findByEmail(any())).thenReturn(FIND_BY_EMAIL_RESPONSE_VIEW);

        LoginUserResponseView user = userService.login(USER_LOGIN_REQUEST_DTO);

        softly.assertThat(user.getAccessToken()).isNotNull();
        softly.assertThat(user.getTokenType()).isEqualTo(BEARER_TOKEN_TYPE);
    }

    @DisplayName("회원의 ID 로 상세정보가 되는지")
    @Test
    public void findById(SoftAssertions softly) {
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(TEST_USER));

        FindByEmailResponseView user = userService.findById(KIM_ID);

        softly.assertThat(user.getEmail()).isEqualTo(KIM_EMAIL);
        softly.assertThat(user.getName()).isEqualTo(KIM_NAME);
    }

}
