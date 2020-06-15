package reuse.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.chat.domain.ChatRoom;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListChatRoomsResponseView {
    private List<ChatRoom> chatRooms;

    @Builder
    public ListChatRoomsResponseView(List<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public static ListChatRoomsResponseView toDto(List<ChatRoom> chatRooms) {
        return ListChatRoomsResponseView.builder()
                .chatRooms(chatRooms)
                .build();
    }

    public int getSize() {
        return this.chatRooms.size();
    }
}
