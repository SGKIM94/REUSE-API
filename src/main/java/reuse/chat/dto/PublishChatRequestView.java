package reuse.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PublishChatRequestView {
    private String message;
    private Integer roomId;

    @Builder
    public PublishChatRequestView(String message, Integer roomId) {
        this.message = message;
        this.roomId = roomId;
    }
}
