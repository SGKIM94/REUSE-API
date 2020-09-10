package reuse.chat.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import reuse.domain.AbstractEntity;
import reuse.domain.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ChatMessage extends AbstractEntity {
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    private MessageType type;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User sender;

    private String message;

    @ManyToOne
    @JoinColumn(name="chat_room_id")
    private ChatRoom chatRoom;

    @Builder
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

    public MessageType getType() {
        return type;
    }

    public boolean isJoinMessageType() {
        return ChatMessage.MessageType.JOIN.equals(getType());
    }

    public void publishJoinMessage() {
        message = sender.getName() + "님이 입장하였습니다.";
    }

    public String getMessage() {
        return message;
    }

    public Long getRoomId() {
        return chatRoom.getId();
    }
}
