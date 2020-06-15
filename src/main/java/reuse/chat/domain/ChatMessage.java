package reuse.chat.domain;

import reuse.domain.AbstractEntity;
import reuse.domain.User;

import javax.persistence.*;

@Entity
public class ChatMessage extends AbstractEntity {
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    private MessageType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User sender;

    private String message;

    @ManyToOne
    @JoinColumn(name="chat_room_id")
    private ChatRoom chatRoom;

    public ChatMessage(MessageType type, User sender, String message, ChatRoom chatRoom) {
        this.type = type;
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
    }

    public ChatMessage(long id, MessageType type, User sender, String message, ChatRoom chatRoom) {
        super(id);
        this.type = type;
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
    }
}
