package reuse.docs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import reuse.AbstractDocumentationTest;
import reuse.FieldsSnippet;
import reuse.service.FavoriteService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reuse.fixture.FavoriteFixture.*;


@WebMvcTest
public class FavoriteDocumentationTest extends AbstractDocumentationTest {
    public static final String FAVORITE_SNIPPET_ID = "id";
    public static final String FAVORITE_BASE_URL = "/favorites";

    @MockBean
    private FavoriteService favoriteService;
    private FieldsSnippet fieldsSnippet;

    @BeforeEach
    void setUp() {
        fieldsSnippet = new FieldsSnippet();
    }

    @DisplayName("즐겨찾기를 추가하는 Docs 이 생성되는지")
    @Test
    void create() throws Exception {
        //given
        given(favoriteService.save(any(), any())).willReturn(FAVORITE_CREATE_RESPONSE_VIEW);

        //when
        this.mockMvc.perform(post(FAVORITE_BASE_URL)
                .content(getContentWithView(FAVORITE_CREATE_REQUEST_VIEW))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("favorites/create", getAuthorizationHeaderSnippet()
                        , getFavoriteRequestFieldsSnippet(), getFavoriteResponseFieldsSnippet()))
                .andDo(print());
    }

    @DisplayName("사용자에 추가되어있는 Favorite 를 가져오는 Docs 가 생성되는지")
    @Test
    void findByUser() throws Exception {
        //given
        given(favoriteService.save(any(), any())).willReturn(FAVORITE_CREATE_RESPONSE_VIEW);

        //when
        this.mockMvc.perform(get(FAVORITE_BASE_URL)
                .content(getContentWithView(FAVORITE_CREATE_REQUEST_VIEW))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("favorites/findByUser", getAuthorizationHeaderSnippet(),
                        getFavoriteUserFieldSnippet(), getFavoriteResponseFieldsSnippet()))
                .andDo(print());
    }

    @DisplayName("사용자에 추가되어있는 Favorite 를 삭제하는 Docs 가 생성되는지")
    @Test
    void deleteItem() throws Exception {
        //given
        given(favoriteService.save(any(), any())).willReturn(FAVORITE_CREATE_RESPONSE_VIEW);

        //when
        this.mockMvc.perform(delete(FAVORITE_BASE_URL + FAVORITE_ID)
                .content(getContentWithView(FAVORITE_CREATE_REQUEST_VIEW))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("favorites/deleteItem", getAuthorizationHeaderSnippet(),
                        getFavoriteUserFieldSnippet(), getFavoriteResponseFieldsSnippet()))
                .andDo(print());
    }

    private RequestFieldsSnippet getFavoriteUserFieldSnippet() {
        return requestFields(
                getUserFieldSnippet()
        );
    }

    private RequestFieldsSnippet getFavoriteRequestFieldsSnippet() {
        return requestFields(
                fieldsSnippet.writeNumberSnippetDescription("id", "The favorite's id"),
                fieldsSnippet.writeStringSnippetDescription("type", "The favorite`s type")
        );
    }

    private ResponseFieldsSnippet getFavoriteResponseFieldsSnippet() {
        return responseFields(
                fieldsSnippet.writeNumberSnippetDescription(FAVORITE_SNIPPET_ID, "favorite id"),
                getUserFieldSnippet(),
                fieldsSnippet.writeNumberSnippetDescription("item", "The favorite`s item")
        );
    }

    private FieldDescriptor getUserFieldSnippet() {
        return fieldsSnippet.writeNumberSnippetDescription("user", "The favorite`s user");
    }

    private RequestHeadersSnippet getAuthorizationHeaderSnippet() {
        return fieldsSnippet.getAuthorizationHeaderSnippet("Bearer auth credentials");
    }
}
