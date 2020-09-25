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
import static reuse.chat.fixture.ChatFixture.TEST_CHAT_MESSAGE;
import static reuse.chat.fixture.ChatFixture.TEST_CHAT_MESSAGE_DTO;

@ExtendWith(SpringExtension.class)
public class ChatMessageServiceTest {
    private ChatMessageService chatMessageService;

    @MockBean
    private SimpMessageSendingOperations messageSendingOperations;

    @MockBean
    private ChatMessageRepository chatMessageRepository;

    @BeforeEach
    void setUp() {
        chatMessageService = new ChatMessageService(messageSendingOperations, chatMessageRepository);
    }

    @DisplayName("입장 시 입장 메시지가 발송되는지")
    @Test
    public void publishJoinMessage() {
        chatMessageService.publishMessage(TEST_CHAT_MESSAGE_DTO);
    }

    @DisplayName("채팅 메시지가 발송되는지")
    @Test
    public void publish() {
        //when
        chatMessageService.publishMessage(TEST_CHAT_MESSAGE_DTO);

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
}
