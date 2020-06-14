package reuse.chat.domain;

import lombok.Builder;
import reuse.domain.AbstractEntity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class ChatRoom extends AbstractEntity {
    private String roomId;
    private String name;

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public static ChatRoom create(String name) {
        return ChatRoom.builder()
                .name(name)
                .roomId(UUID.randomUUID().toString())
                .build();
    }
}
