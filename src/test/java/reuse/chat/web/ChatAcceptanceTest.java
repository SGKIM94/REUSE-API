package reuse.chat.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.chat.domain.ChatRoom;
import reuse.chat.dto.ListChatRoomsResponseView;
import reuse.dto.board.CreateBoardResponseView;
import reuse.security.TokenAuthenticationService;
import reuse.web.CreateWebClientTest;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.chat.fixture.ChatFixture.*;
import static reuse.fixture.CategoryFixture.CREATE_CATEGORY_REQUEST_VIEW;

public class ChatAcceptanceTest extends AbstractAcceptanceTest {
    public static final String CHAT_BASE_URL = "/chats";

    private CreateWebClientTest restWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private String socialTokenId;

    @BeforeEach
    void setUp() {
        this.restWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();

        socialTokenId = restWebClientTest.createUser();
        restWebClientTest.createCategory(CREATE_CATEGORY_REQUEST_VIEW, getJwt());
    }

    @DisplayName("채팅을 하기 위해 방을 생성하이 가능한지")
    @Test
    public void create() {
        //when
        EntityExchangeResult<CreateBoardResponseView> expectResponse
                = restWebClientTest.postMethodWithAuthAcceptance
                (CHAT_BASE_URL, TEST_CHAT_ROOM_NAME, CreateBoardResponseView.class, getJwt());

        //then
        HttpStatus status = expectResponse.getStatus();
        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
    }

    @DisplayName("생성된 채팅방을 특정 ID 로 조회 가능한지")
    @Test
    public void findById() {
        CreateBoardResponseView chatRoom = createChatRoom(TEST_CHAT_ROOM_NAME);

        //when
        EntityExchangeResult<ChatRoom> expectResponse
                = restWebClientTest.getMethodWithAuthAcceptance
                (CHAT_BASE_URL + "/" + chatRoom.getId(), ChatRoom.class, getJwt());

        //then
        HttpStatus status = expectResponse.getStatus();
        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
    }

    @DisplayName("생성된 채팅방을 모두 조회 가능한지")
    @Test
    public void findAll() {
        createChatRoom(TEST_CHAT_ROOM_NAME);
        createChatRoom(TEST_SECOND_CHAT_ROOM_NAME);
        createChatRoom(TEST_THIRD_CHAT_ROOM_NAME);

        //when
        EntityExchangeResult<ListChatRoomsResponseView> expectResponse
                = restWebClientTest.getMethodWithAuthAcceptance
                (CHAT_BASE_URL, ListChatRoomsResponseView.class, getJwt());

        //then
        HttpStatus status = expectResponse.getStatus();
        ListChatRoomsResponseView responseBody = expectResponse.getResponseBody();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseBody.getSize()).isGreaterThan(3);
    }

    @DisplayName("메시지가 보내지는지")
    @Test
    public void publishMessage() {
        //when
        EntityExchangeResult<Void> expectResponse
                = restWebClientTest.getMethodWithAuthAcceptance
                (CHAT_BASE_URL + "/message", Void.class, getJwt());

        //then
        HttpStatus status = expectResponse.getStatus();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
    }


    public CreateBoardResponseView createChatRoom(String name) {
        return restWebClientTest.postMethodWithAuthAcceptance
                (CHAT_BASE_URL, name, CreateBoardResponseView.class, getJwt())
                .getResponseBody();
    }

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
    }
}
