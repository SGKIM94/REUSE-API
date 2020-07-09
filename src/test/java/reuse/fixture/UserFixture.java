package reuse.fixture;

import reuse.domain.User;
import reuse.dto.user.CreateUserRequestView;
import reuse.dto.user.FindByIdResponseView;
import reuse.dto.user.LoginUserRequestView;

public class UserFixture extends CommonFixture {
    public static final Long TEST_USER_ID = 1L;
    public static final String TEST_USER_NAME = "김상구";
    public static final String TEST_USER_EMAIL = "sgkim94@github.com";
    public static final String TEST_USER_PASSWORD = "password";
    public static final String TEST_SOCIAL_TOKEN_ID = "tokenId";
    public static final String TEST_SECOND_SOCIAL_TOKEN_ID = "secondTokenId";
    public static final String TEST_NAVER_SOCIAL_TYPE = "naver";

    public static final CreateUserRequestView USER_SIGH_UP_REQUEST_DTO = CreateUserRequestView.builder()
            .name(TEST_USER_NAME).socialTokenId(TEST_SOCIAL_TOKEN_ID).socialType(TEST_NAVER_SOCIAL_TYPE).build();
    public static final LoginUserRequestView USER_LOGIN_REQUEST_DTO = LoginUserRequestView.builder().id(TEST_USER_ID)
            .socialTokenId(TEST_SOCIAL_TOKEN_ID).socialType(TEST_NAVER_SOCIAL_TYPE).build();
    public static final FindByIdResponseView FIND_BY_EMAIL_RESPONSE_VIEW = new FindByIdResponseView(TEST_USER_ID, TEST_USER_EMAIL, TEST_USER_PASSWORD);

    public static CreateUserRequestView getCreateUserRequestView(User user) {
        return CreateUserRequestView.builder().name(user.getName()).socialTokenId(user.getSocialTokenId()).build();
    }

    public static LoginUserRequestView getLoginUserRequestView(User user) {
        return new LoginUserRequestView(TEST_USER_ID, user.getSocialTokenId(), TEST_NAVER_SOCIAL_TYPE);
    }

    public static final User TEST_USER = new User(DEFAULT_ID, TEST_SOCIAL_TOKEN_ID, TEST_USER_NAME, TEST_NAVER_SOCIAL_TYPE);
    public static final User TEST_SECOND_USER = new User(SECOND_ID, TEST_SECOND_SOCIAL_TOKEN_ID, TEST_USER_NAME, TEST_NAVER_SOCIAL_TYPE);
    public static final User LOGIN_USER = User.builder().socialTokenId(TEST_SOCIAL_TOKEN_ID).socialType(TEST_NAVER_SOCIAL_TYPE).build();
}
