package reuse.chat.fixture;

import reuse.chat.domain.ChatMessage;
import reuse.chat.domain.ChatRoom;
import reuse.chat.dto.CreateChatRequestView;
import reuse.chat.dto.PublishChatRequestView;
import reuse.fixture.CommonFixture;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.UserFixture.TEST_USER;

public class ChatFixture extends CommonFixture {
    public static final Integer TEST_ROOM_ID = 1;
    public static final String TEST_CHAT_ROOM_NAME = "첫번째 채팅방";
    public static final String TEST_SECOND_CHAT_ROOM_NAME = "두번째 채팅방";
    public static final String TEST_THIRD_CHAT_ROOM_NAME = "세번째 채팅방";

    public static final String TEST_MESSAGE = "테스트 메시지입니다.!";

    public static final CreateChatRequestView CREATE_CHAT_REQUEST_VIEW = CreateChatRequestView.builder()
            .name(TEST_CHAT_ROOM_NAME).build();

    public static final CreateChatRequestView SECOND_CREATE_CHAT_REQUEST_VIEW = CreateChatRequestView.builder()
            .name(TEST_SECOND_CHAT_ROOM_NAME).build();

    public static final CreateChatRequestView THIRD_CREATE_CHAT_REQUEST_VIEW = CreateChatRequestView.builder()
            .name(TEST_THIRD_CHAT_ROOM_NAME).build();

    public static final ChatRoom TEST_CHAT_ROOM = ChatRoom.builder()
            .name(TEST_CHAT_ROOM_NAME).owner(TEST_USER).build();

    public static final ChatRoom TEST_SECOND_CHAT_ROOM = ChatRoom.builder()
            .name(TEST_SECOND_CHAT_ROOM_NAME).owner(TEST_USER).build();

    public static final ChatRoom TEST_THIRD_CHAT_ROOM = ChatRoom.builder()
            .name(TEST_THIRD_CHAT_ROOM_NAME).owner(TEST_USER).build();

    public static final List<ChatRoom> TEST_CHAT_ROOMS
            = Arrays.asList(TEST_CHAT_ROOM, TEST_SECOND_CHAT_ROOM, TEST_THIRD_CHAT_ROOM);

    public static final ChatMessage TEST_JOIN_CHAT_MESSAGE = ChatMessage.builder()
            .type(ChatMessage.MessageType.JOIN).chatRoom(TEST_CHAT_ROOM).sender(TEST_USER).message(TEST_MESSAGE).build();

    public static final ChatMessage TEST_CHAT_MESSAGE = ChatMessage.builder()
            .type(ChatMessage.MessageType.CHAT).chatRoom(TEST_CHAT_ROOM).sender(TEST_USER).message(TEST_MESSAGE).build();

    public static final PublishChatRequestView TEST_CHAT_MESSAGE_DTO = PublishChatRequestView.builder()
            .type(ChatMessage.MessageType.CHAT).message(TEST_MESSAGE).roomId(TEST_ROOM_ID).build();
}
