package reuse.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import reuse.chat.domain.ChatMessage;
import reuse.chat.domain.ChatRoom;
import reuse.chat.dto.PublishChatRequestView;
import reuse.chat.repository.ChatMessageRepository;
import reuse.domain.User;

@Service
@Slf4j
public class ChatMessageService {
    private final SimpMessageSendingOperations messageSendingOperations;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessageService(SimpMessageSendingOperations messageSendingOperations, ChatMessageRepository chatMessageRepository, ChatRoomService chatRoomService) {
        this.chatMessageRepository = chatMessageRepository;
        this.messageSendingOperations = messageSendingOperations;
        this.chatRoomService = chatRoomService;
    }

    public void publishMessage(PublishChatRequestView message, User sender) {
        if (message.isJoinMessageType()) {
            message.publishJoinMessage();
        }

        log.info("메시지가 들어왔습니다.");

        ChatRoom chatRoom = chatRoomService.findById(message.getRoomId());
        create(message.toEntity(chatRoom, sender));

        messageSendingOperations.convertAndSend("/sub/chats/" + message.getRoomId(), message);
    }

    public ChatMessage create(ChatMessage chat) {
        return chatMessageRepository.save(chat);
    }
}
