package reuse.chat.domain;

import lombok.Builder;
import reuse.domain.AbstractEntity;
import reuse.domain.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ChatRoom extends AbstractEntity {
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
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
                .owner(user)
                .name(name)
                .build();
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }
}
