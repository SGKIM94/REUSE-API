package reuse.fixture;

import reuse.domain.User;
import reuse.dto.user.CreateUserRequestView;
import reuse.dto.user.FindByEmailResponseView;
import reuse.dto.user.FindBySocialTokenIdResponseView;
import reuse.dto.user.LoginUserRequestView;

public class UserFixture {
    public static final Long KIM_ID = 1L;
    public static final String KIM_NAME = "김상구";
    public static final String KIM_EMAIL = "sgkim94@github.com";
    public static final String KIM_PASSWORD = "password";
    public static final String SOCIAL_TOKEN_ID = "tokenId";
    public static final String NAVER_SOCIAL_TYPE = "naver";
    public static final CreateUserRequestView USER_SIGH_UP_REQUEST_DTO = new CreateUserRequestView(1L, KIM_EMAIL, KIM_NAME, KIM_PASSWORD);
    public static final LoginUserRequestView USER_LOGIN_REQUEST_DTO = new LoginUserRequestView(KIM_EMAIL, KIM_PASSWORD);
    public static final FindByEmailResponseView FIND_BY_EMAIL_RESPONSE_VIEW = new FindByEmailResponseView(KIM_ID, KIM_EMAIL, KIM_PASSWORD);
    public static final FindBySocialTokenIdResponseView FIND_BY_SOCIAL_TOKEN_ID_RESPONSE_VIEW = new FindBySocialTokenIdResponseView(KIM_ID, SOCIAL_TOKEN_ID, NAVER_SOCIAL_TYPE);

    public static CreateUserRequestView getCreateUserRequestView(User user) {
        return new CreateUserRequestView(1L, SOCIAL_TOKEN_ID, NAVER_SOCIAL_TYPE user.getName());
    }

    public static LoginUserRequestView getLoginUserRequestView(User user) {
        return new LoginUserRequestView(user.getSocialTokenId(), NAVER_SOCIAL_TYPE);
    }

    public static final User TEST_USER = new User(KIM_ID, SOCIAL_TOKEN_ID, KIM_NAME, NAVER_SOCIAL_TYPE);
    public static final User LOGIN_USER = User.builder().socialTokenId(SOCIAL_TOKEN_ID).socialType(NAVER_SOCIAL_TYPE).build();
}
