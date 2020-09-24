package reuse.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.chat.domain.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
