package reuse.chat.fixture;

import reuse.chat.domain.ChatRoom;
import reuse.fixture.CommonFixture;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.UserFixture.TEST_USER;

public class ChatFixture extends CommonFixture {
    public static final String TEST_CHAT_ROOM_NAME = "첫번째 채팅방";
    public static final String TEST_SECOND_CHAT_ROOM_NAME = "두번째 채팅방";
    public static final String TEST_THIRD_CHAT_ROOM_NAME = "세번째 채팅방";

    public static final ChatRoom TEST_CHAT_ROOM = ChatRoom.builder()
            .name(TEST_CHAT_ROOM_NAME).owner(TEST_USER).build();

    public static final ChatRoom TEST_SECOND_CHAT_ROOM = ChatRoom.builder()
            .name(TEST_SECOND_CHAT_ROOM_NAME).owner(TEST_USER).build();

    public static final ChatRoom TEST_THIRD_CHAT_ROOM = ChatRoom.builder()
            .name(TEST_THIRD_CHAT_ROOM_NAME).owner(TEST_USER).build();

    public static final List<ChatRoom> TEST_CHAT_ROOMS
            = Arrays.asList(TEST_CHAT_ROOM, TEST_SECOND_CHAT_ROOM, TEST_THIRD_CHAT_ROOM);
}
