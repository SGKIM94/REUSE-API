package reuse.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import reuse.chat.domain.ChatMessage;
import reuse.chat.dto.PublishChatRequestView;
import reuse.chat.repository.ChatMessageRepository;

@Service
@Slf4j
public class ChatMessageService {
    private final SimpMessageSendingOperations messageSendingOperations;
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(SimpMessageSendingOperations messageSendingOperations, ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.messageSendingOperations = messageSendingOperations;
    }

    public void publishMessage(PublishChatRequestView message) {
        if (message.isJoinMessageType()) {
            message.publishJoinMessage();
        }

        log.info("메시지가 들어왔습니다.");


        //TODO: 채팅 메시지가 들어오면 저장하고 해당 방에 있는 모든 message 를 리턴하도록 수정
        messageSendingOperations.convertAndSend("/sub/chats/" + message.getRoomId(), message);
    }

    public ChatMessage create(ChatMessage chat) {
        return chatMessageRepository.save(chat);
    }
}
