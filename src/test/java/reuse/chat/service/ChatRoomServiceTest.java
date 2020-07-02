package reuse.chat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.chat.domain.ChatRoom;
import reuse.chat.dto.ListChatRoomsResponseView;
import reuse.chat.repository.ChatRoomRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.chat.fixture.ChatFixture.*;
import static reuse.fixture.UserFixture.TEST_USER;

@ExtendWith(SpringExtension.class)
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

        ChatRoom savedRoom = chatRoomService.create(CREATE_CHAT_REQUEST_VIEW, TEST_USER);

        assertThat(savedRoom.getId()).isNotNull();
        assertThat(savedRoom.getName()).isEqualTo(TEST_CHAT_ROOM_NAME);
    }

    @DisplayName("특정 채팅방을 조회할 수 있는지")
    @Test
    public void findById() {
        when(chatRoomRepository.findById(DEFAULT_ID)).thenReturn(java.util.Optional.ofNullable(TEST_CHAT_ROOM));

        ChatRoom chatRoom = chatRoomService.retrieve(DEFAULT_ID);

        assertThat(chatRoom.getId()).isNotNull();
        assertThat(chatRoom.getName()).isEqualTo(TEST_CHAT_ROOM_NAME);
    }

    @DisplayName("모든 채팅방을 조회할 수 있는지")
    @Test
    public void findAll() {
        when(chatRoomRepository.findAll()).thenReturn(TEST_CHAT_ROOMS);

        ListChatRoomsResponseView chatRooms = chatRoomService.findAll();

        assertThat(chatRooms.getSize()).isEqualTo(3);
    }
}
