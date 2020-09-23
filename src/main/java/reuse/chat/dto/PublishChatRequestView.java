package reuse.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.chat.domain.ChatMessage;

@Getter
@Setter
@NoArgsConstructor
public class PublishChatRequestView {
    private String message;
    private Integer roomId;
    private ChatMessage.MessageType type;

    @Builder
    public PublishChatRequestView(String message, Integer roomId, ChatMessage.MessageType type) {
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
}
