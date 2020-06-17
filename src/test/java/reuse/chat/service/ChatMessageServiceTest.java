package reuse.chat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static reuse.chat.fixture.ChatFixture.TEST_CHAT_MESSAGE;
import static reuse.chat.fixture.ChatFixture.TEST_JOIN_CHAT_MESSAGE;

@ExtendWith(SpringExtension.class)
public class ChatMessageServiceTest {
    private ChatMessageService chatMessageService;

    @MockBean
    private SimpMessageSendingOperations messageSendingOperations;

    @BeforeEach
    void setUp() {
        chatMessageService = new ChatMessageService(messageSendingOperations);
    }

    @DisplayName("입장 시 입장 메시지가 발송되는지")
    @Test
    public void publishJoinMessage() {
        chatMessageService.publishMessage(TEST_JOIN_CHAT_MESSAGE);
    }

    @DisplayName("채팅 메시지가 발송되는지")
    @Test
    public void publish() {
        //when
        chatMessageService.publishMessage(TEST_CHAT_MESSAGE);

        //then
        verify(messageSendingOperations).convertAndSend(any(), (Object) any());
    }
}
