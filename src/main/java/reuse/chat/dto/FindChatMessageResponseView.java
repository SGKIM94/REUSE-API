package reuse.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.chat.domain.ChatMessage;

@Getter
@Setter
@NoArgsConstructor
public class FindChatMessageResponseView {
    private ChatMessage.MessageType type;
    private Long senderId;
    private String message;
    private Long roomId;

    @Builder
    public FindChatMessageResponseView(ChatMessage chatMessage) {
        this.type = chatMessage.getType();
        this.message = chatMessage.getMessage();
        this.senderId = chatMessage.getSenderId();
        this.roomId = chatMessage.getRoomId();
    }

    public FindChatMessageResponseView toDto(ChatMessage chatMessage) {
        return FindChatMessageResponseView.builder()
                .chatMessage(chatMessage)
                .build();
    }
}
