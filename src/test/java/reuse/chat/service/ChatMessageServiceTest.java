package reuse.chat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.chat.repository.ChatMessageRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static reuse.chat.fixture.ChatFixture.*;
import static reuse.fixture.CommonFixture.DEFAULT_ID;
import static reuse.fixture.UserFixture.TEST_USER;

@ExtendWith(SpringExtension.class)
public class ChatMessageServiceTest {
    private ChatMessageService chatMessageService;

    @MockBean
    private SimpMessageSendingOperations messageSendingOperations;

    @MockBean
    private ChatMessageRepository chatMessageRepository;

    @MockBean
    private ChatRoomService chatRoomService;

    @BeforeEach
    void setUp() {
        chatMessageService = new ChatMessageService(messageSendingOperations, chatMessageRepository, chatRoomService);
    }

    @DisplayName("입장 시 입장 메시지가 발송되는지")
    @Test
    public void publishJoinMessage() {
        chatMessageService.publishMessage(TEST_CHAT_MESSAGE_DTO, LIST_CHAT_MESSAGE_RESPONSE_VIEW);
    }

    @DisplayName("채팅 메시지가 발송되는지")
    @Test
    public void publish() {
        //when
        chatMessageService.publishMessage(TEST_CHAT_MESSAGE_DTO, LIST_CHAT_MESSAGE_RESPONSE_VIEW);

        //then
        verify(messageSendingOperations).convertAndSend(any(), (Object) any());
    }

    @DisplayName("채팅 메시지가 DB 에 저장되는지")
    @Test
    public void create() {
        //when
        chatMessageService.create(TEST_CHAT_MESSAGE);

        //then
        verify(chatMessageRepository).save(TEST_CHAT_MESSAGE);
    }

    @DisplayName("메시지를 publish 할 때 대화 내용을 저장하는지")
    @Test
    public void createByPublishChatDto() {
        //when
        chatMessageService.createByPublishChatDto(TEST_CHAT_MESSAGE_DTO, TEST_USER);

        //then
        verify(chatRoomService).findById(DEFAULT_ID);
        verify(chatMessageRepository).save(TEST_CHAT_MESSAGE);
    }

    @DisplayName("모든 저장된 메시지를 DTO 로 가져온느지")
    @Test
    public void findAll() {
        //when
        chatMessageService.findAll();

        //then
        verify(chatMessageRepository).findAll();
    }

    @DisplayName("해당 방에 있는 모든 chatMessages 를 가져올 수 있는지")
    @Test
    public void findByChatRoomId() {
        //when
        chatMessageService.findByChatRoomId(DEFAULT_ID);

        //then
        verify(chatMessageRepository).findByChatRoomId(DEFAULT_ID);
    }

}
