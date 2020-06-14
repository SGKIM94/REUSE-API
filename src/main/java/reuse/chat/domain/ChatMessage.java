package reuse.chat.domain;

import reuse.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ChatMessage extends AbstractEntity {
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    private MessageType type;

    private String sender;

    private String message;

    @ManyToOne
    @JoinColumn(name="chat_room_id")
    private ChatRoom chatRoom;

    public ChatMessage(MessageType type, String sender, String message, ChatRoom chatRoom) {
        this.type = type;
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
    }

    public ChatMessage(long id, MessageType type, String sender, String message, ChatRoom chatRoom) {
        super(id);
        this.type = type;
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
    }
}
