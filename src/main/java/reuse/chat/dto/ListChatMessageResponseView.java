package reuse.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.chat.domain.ChatMessage;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ListChatMessageResponseView {
    private List<FindChatMessageResponseView> chatMessages;

    @Builder
    private ListChatMessageResponseView(List<FindChatMessageResponseView> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public static ListChatMessageResponseView toDto(List<ChatMessage> chatMessages) {
        List<FindChatMessageResponseView> messages = chatMessages.stream()
                .map(FindChatMessageResponseView::new)
                .collect(Collectors.toList());

        return new ListChatMessageResponseView(messages);
    }
}
