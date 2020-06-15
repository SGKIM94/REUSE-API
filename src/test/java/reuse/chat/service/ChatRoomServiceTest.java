package reuse.chat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reuse.chat.domain.ChatRoom;
import reuse.chat.repository.ChatRoomRepository;
import reuse.service.ChatRoomService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.chat.fixture.ChatFixture.TEST_CHAT_ROOM;
import static reuse.chat.fixture.ChatFixture.TEST_CHAT_ROOM_NAME;
import static reuse.fixture.UserFixture.TEST_USER;

@SpringBootTest
public class ChatRoomServiceTest {
    private ChatRoomService chatRoomService;

    @MockBean
    private ChatRoomRepository chatRoomRepository;

    @BeforeEach
    void setUp() {
        chatRoomService = new ChatRoomService(chatRoomRepository);
    }

    @DisplayName("채팅방이 생성되는지")
    @Test
    public void create() {
        when(chatRoomRepository.save(any())).thenReturn(TEST_CHAT_ROOM);

        ChatRoom savedRoom = chatRoomService.create(TEST_CHAT_ROOM_NAME, TEST_USER);

        assertThat(savedRoom.getId()).isNotNull();
        assertThat(savedRoom.getName()).isEqualTo(TEST_CHAT_ROOM_NAME);
    }
}
