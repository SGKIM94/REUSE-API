package reuse.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.chat.domain.ChatRoom;

public interface ChatMessageRepository extends JpaRepository<ChatRoom, Long> {
}
