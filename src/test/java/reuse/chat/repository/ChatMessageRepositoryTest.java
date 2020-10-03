package reuse.chat.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.chat.domain.ChatMessage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.chat.fixture.ChatFixture.TEST_CHAT_MESSAGE;
import static reuse.chat.fixture.ChatFixture.TEST_JOIN_CHAT_MESSAGE;
import static reuse.fixture.BoardFixture.DEFAULT_ID;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ChatMessageRepositoryTest {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @DisplayName("특정 방에 존재하는 모든 메시지들을 가져오는지")
    @Test
    @Sql(scripts = {"/insert-users.sql", "/insert-chat-rooms.sql"})
    public void findAllByCategory() {
        //given
        chatMessageRepository.save(TEST_CHAT_MESSAGE);
        chatMessageRepository.save(TEST_JOIN_CHAT_MESSAGE);

        //when
        List<ChatMessage> chatRooms = chatMessageRepository.findByChatRoomId(DEFAULT_ID);


        //then
        assertThat(chatRooms.size()).isGreaterThan(1);
    }
}
