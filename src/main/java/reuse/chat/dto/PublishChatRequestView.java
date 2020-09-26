package reuse.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.chat.domain.ChatMessage;
import reuse.chat.domain.ChatRoom;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class PublishChatRequestView {
    private String message;
    private Long roomId;
    private ChatMessage.MessageType type;

    @Builder
    public PublishChatRequestView(String message, Long roomId, ChatMessage.MessageType type) {
        this.message = message;
        this.roomId = roomId;
        this.type = type;
    }

    public boolean isJoinMessageType() {
        return ChatMessage.MessageType.JOIN.equals(getType());
    }

    public void publishJoinMessage() {
        this.message = "입장하였습니다.";
    }

    public ChatMessage toEntity(ChatRoom chatRoom, User sender) {
        return ChatMessage.builder()
                .message(message)
                .chatRoom(chatRoom)
                .sender(sender)
                .type(type)
                .build();
    }
}
