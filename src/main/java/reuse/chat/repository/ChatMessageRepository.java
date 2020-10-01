package reuse.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.chat.domain.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomId(Long roomId);
}
