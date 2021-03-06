package reuse.chat.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.chat.fixture.ChatFixture.TEST_CHAT_MESSAGE;
import static reuse.chat.fixture.ChatFixture.TEST_JOIN_CHAT_MESSAGE;

public class ChatMessageTest {

    @DisplayName("입장 시 입장 메시지가 저장되는지")
    @Test
    public void publishJoinMessage() {
        //given
        ChatMessage joinChatMessage = TEST_JOIN_CHAT_MESSAGE;

        //when
        joinChatMessage.publishJoinMessage();

        //then
        assertThat(joinChatMessage.getMessage()).isEqualTo("김상구님이 입장하였습니다.");
    }

    @DisplayName("입장 메시지인 경우 참을 리턴하는지")
    @Test
    public void isJoinMessageType() {
        //when
        boolean messageType = TEST_JOIN_CHAT_MESSAGE.isJoinMessageType();

        //then
        assertThat(messageType).isTrue();
    }

    @DisplayName("입장 메시지가 아닌 경우 거짓을 리턴하는지")
    @Test
    public void isNotJoinMessageType() {
        //when
        boolean messageType = TEST_CHAT_MESSAGE.isJoinMessageType();

        //then
        assertThat(messageType).isFalse();
    }

}
