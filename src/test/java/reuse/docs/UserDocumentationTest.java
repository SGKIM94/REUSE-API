package reuse.docs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import reuse.AbstractDocumentationTest;
import reuse.FieldsSnippet;
import reuse.domain.User;
import reuse.dto.user.CreateUserRequestView;
import reuse.dto.user.CreateUserResponseView;
import reuse.dto.user.LoginUserRequestView;
import reuse.service.UserService;
import reuse.web.UserController;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reuse.fixture.UserFixture.*;

@Disabled
@WebMvcTest(UserController.class)
public class UserDocumentationTest extends AbstractDocumentationTest {
    public static final String TEST_USER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib29yd29uaWVAZW1haWwuY29tIiwiaWF0IjoxNTgxOTg1NjYzLCJleHAiOjE1ODE5ODkyNjN9.nL07LEhgTVzpUdQrOMbJq-oIce_idEdPS62hB2ou2hg";
    public static final String USER_ID = "id";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_NAME = "name";
    public static final String USER_BASE_URL = "/users";
    public static final String EMAIL_PATH = "$.email";
    private static final String BEARER_TYPE = "BEARER";

    @MockBean
    private UserService userService;
    private FieldsSnippet fieldsSnippet;

    @BeforeEach
    void setUp() {
        fieldsSnippet = new FieldsSnippet();
    }

    @DisplayName("사용자를 생성하는 API 의 Rest Docs 를 생성 하는지")
    @Test
    void create() throws Exception {
        //given
        User user = TEST_USER;
        CreateUserRequestView createUserRequestView = getCreateUserRequestView(user);

        given(userService.singUp(any())).willReturn(CreateUserResponseView.toDto(TEST_USER));

       //when
        this.mockMvc.perform(post(USER_BASE_URL)
                .content(getContentWithView(createUserRequestView))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("users/create", getUserRequestFieldsSnippet(), getUserResponseFieldsSnippet()))
                .andDo(print());
    }

    @DisplayName("사용자를 조회하는 API 의 Rest Docs 를 생성 하는지")
    @Test
    void retrieveUser() throws Exception {
        //given
        given(userService.findById(any())).willReturn(FIND_BY_EMAIL_RESPONSE_VIEW);

        //when
        this.mockMvc.perform(get(USER_BASE_URL)
                .header(HttpHeaders.AUTHORIZATION, BEARER_TYPE + " " + TEST_USER_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(EMAIL_PATH).value(TEST_USER))
                .andDo(document("users/me", getAuthorizationHeaderSnippet(), getUserResponseFieldsSnippet()))
                .andDo(print());
    }

    @DisplayName("사용자 login API 의 Rest Docs 를 생성 하는지")
    @Test
    void login() throws Exception {
        //given
        User user = TEST_USER;
        LoginUserRequestView loginUserRequestView = getLoginUserRequestView(user);

        given(userService.singUp(any())).willReturn(CreateUserResponseView.toDto(TEST_USER));

        //when
        this.mockMvc.perform(post(USER_BASE_URL)
                .content(getContentWithView(loginUserRequestView))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(fieldsSnippet.writeResultDocument("users/login", getLoginRequestFieldsSnippet()))
                .andDo(print());
    }

    private RequestFieldsSnippet getLoginRequestFieldsSnippet() {
        return requestFields(
            fieldsSnippet.writeStringSnippetDescription(USER_EMAIL, "The user's email address"),
            fieldsSnippet.writeStringSnippetDescription(USER_PASSWORD, "The user's password"));
    }

    private RequestFieldsSnippet getUserRequestFieldsSnippet() {
        return requestFields(
                fieldsSnippet.writeNumberSnippetDescription(USER_ID, "The user's id"),
                fieldsSnippet.writeStringSnippetDescription(USER_EMAIL, "The user's email address"),
                fieldsSnippet.writeStringSnippetDescription(USER_PASSWORD, "The user's password"),
                fieldsSnippet.writeStringSnippetDescription(USER_NAME, "The user's name")
        );
    }

    private ResponseFieldsSnippet getUserResponseFieldsSnippet() {
        return responseFields(
                fieldsSnippet.writeNumberSnippetDescription(USER_ID, "The user's id"),
                fieldsSnippet.writeStringSnippetDescription(USER_EMAIL, "The user's email address"),
                fieldsSnippet.writeStringSnippetDescription(USER_PASSWORD, "The user's password"),
                fieldsSnippet.writeStringSnippetDescription(USER_NAME, "The user's name")
        );
    }

    private RequestHeadersSnippet getAuthorizationHeaderSnippet() {
        return fieldsSnippet.getAuthorizationHeaderSnippet("Bearer auth credentials");
    }
}
