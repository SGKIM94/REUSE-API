package reuse.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import reuse.chat.domain.ChatMessage;
import reuse.chat.domain.ChatRoom;
import reuse.chat.dto.ListChatMessageResponseView;
import reuse.chat.dto.PublishChatRequestView;
import reuse.chat.repository.ChatMessageRepository;
import reuse.domain.User;

import java.util.List;

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

    public void publishMessage(PublishChatRequestView message, ListChatMessageResponseView messages) {
        if (message.isJoinMessageType()) {
            message.publishJoinMessage();
        }

        log.info("메시지가 들어왔습니다.");

        messageSendingOperations.convertAndSend("/sub/chats/" + message.getRoomId(), messages);
    }

    public void createByPublishChatDto(PublishChatRequestView message, User sender) {
        ChatRoom chatRoom = chatRoomService.findById(message.getRoomId());
        create(message.toEntity(chatRoom, sender));
    }

    public ChatMessage create(ChatMessage chat) {
        return chatMessageRepository.save(chat);
    }

    public ListChatMessageResponseView findAll() {
        List<ChatMessage> messages = chatMessageRepository.findAll();
        return ListChatMessageResponseView.toDto(messages);
    }
}
