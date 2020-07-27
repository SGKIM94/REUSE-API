package reuse.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import reuse.chat.domain.ChatMessage;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messageTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = Objects.requireNonNull(redisTemplate
                    .getStringSerializer()
                    .deserialize(message.getBody()))
                    .toString();

            ChatMessage roomMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
            messageTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), roomMessage);
        } catch (JsonMappingException e) {
            log.error("[REDIS MESSAGE MAPPING ERROR] 채팅 메시지 값을 읽을 때 Json Mapping 오류가 발생하였습니다.");
            log.error("STACK : " + e);
        } catch (JsonProcessingException e) {
            log.error("[REDIS MESSAGE IO ERROR] 채팅 메시지를 읽는 도중 오류가 발생하였씁니다.");
            log.error("STACK : " + e);
        }
    }
}
