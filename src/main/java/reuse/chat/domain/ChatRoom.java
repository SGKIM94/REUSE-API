package reuse.chat.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import reuse.domain.AbstractEntity;
import reuse.domain.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
public class ChatRoom extends AbstractEntity {
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Builder
    public ChatRoom(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public ChatRoom(long id, String name, User owner) {
        super(id);
        this.name = name;
        this.owner = owner;
    }

    public static ChatRoom toEntity(String name, User user) {
        return ChatRoom.builder()
                .name(name)
                .owner(user)
                .build();
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }
}
