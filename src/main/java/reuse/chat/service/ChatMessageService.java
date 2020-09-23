package reuse.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import reuse.chat.dto.PublishChatRequestView;

@Service
@Slf4j
public class ChatMessageService {
    private final SimpMessageSendingOperations messageSendingOperations;

    public ChatMessageService(SimpMessageSendingOperations messageSendingOperations) {
        this.messageSendingOperations = messageSendingOperations;
    }

    public void publishMessage(PublishChatRequestView message) {
        if (message.isJoinMessageType()) {
            message.publishJoinMessage();
        }

        log.info("메시지가 들어왔습니다.");

        messageSendingOperations.convertAndSend("/sub/chats/" + message.getRoomId(), message);
    }
}
