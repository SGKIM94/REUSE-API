package reuse.chat.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import reuse.chat.dto.CreateChatRequestView;
import reuse.domain.AbstractEntity;
import reuse.domain.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ChatRoom extends AbstractEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Builder
    public ChatRoom(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    @Builder
    public ChatRoom(long id, String name, User owner) {
        super(id);
        this.name = name;
        this.owner = owner;
    }

    public static ChatRoom toEntity(CreateChatRequestView chat, User user) {
        return ChatRoom.builder()
                .name(chat.getName())
                .owner(user)
                .build();
    }

    public String getName() {
        return name;
    }
}
