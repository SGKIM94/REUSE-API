package reuse.chat.service;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import reuse.chat.domain.ChatMessage;

@Service
public class ChatMessageService {
    private final SimpMessageSendingOperations messageSendingOperations;

    public ChatMessageService(SimpMessageSendingOperations messageSendingOperations) {
        this.messageSendingOperations = messageSendingOperations;
    }

    public void publishMessage(ChatMessage message) {
        if (message.isJoinMessageType()) {
            message.publishJoinMessage();
        }

        messageSendingOperations.convertAndSend("/sub/chats/" + message.getRoomId(), message);
    }
}
