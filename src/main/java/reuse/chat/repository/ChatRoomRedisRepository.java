package reuse.chat.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;
import reuse.chat.domain.ChatRoom;
import reuse.chat.dto.CreateChatRequestView;
import reuse.chat.service.RedisSubscriber;
import reuse.domain.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ChatRoomRedisRepository {
    public static final String CHAT_ROOM = "CHAT_ROOM";

    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> operations;
    private ChatRoomRepository chatRoomRepository;
    private RedisSubscriber redisSubscriber;


    private Map<String, ChannelTopic> topics;

    @PostConstruct
    public void init() {
        operations = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return operations.values(CHAT_ROOM);
    }

    public ChatRoom findRoomById(String id) {
        return operations.get(CHAT_ROOM, id);
    }

    public ChatRoom createChatRoom(CreateChatRequestView chat, User user) {
        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.toEntity(chat, user));
        operations.put(CHAT_ROOM, String.valueOf(chatRoom.getId()), chatRoom);
        return chatRoom;
    }

    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);

        if (topic == null) {
            topic = new ChannelTopic(roomId);
            redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
            topic.put(roomId, topic);
        }
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }
}
