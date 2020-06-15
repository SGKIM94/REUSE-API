package reuse.chat.fixture;

import reuse.chat.domain.ChatRoom;
import reuse.fixture.CommonFixture;

import static reuse.fixture.UserFixture.TEST_USER;

public class ChatFixture extends CommonFixture {
    public static final String TEST_CHAT_ROOM_NAME = "첫번째 채팅방";

    public static final ChatRoom TEST_CHAT_ROOM = ChatRoom.builder()
            .name(TEST_CHAT_ROOM_NAME).owner(TEST_USER).build();
}
