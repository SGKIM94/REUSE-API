package reuse.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.chat.repository.ChatRoomRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ChatRoomRepositoryTest {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @DisplayName("채팅 방을 REDIS 로 정상적으로 생성되는지")
    @Test
    public void createChatRoom() {
        //given


        //when

        //then
        assertThat().isEqualTo();
    }
}
