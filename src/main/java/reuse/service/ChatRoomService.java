package reuse.service;

import org.springframework.stereotype.Service;
import reuse.chat.domain.ChatRoom;
import reuse.chat.repository.ChatRoomRepository;
import reuse.domain.User;

@Service
public class ChatRoomService {
    private ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public ChatRoom create(String roomName, User loginUser) {
        return chatRoomRepository.save(ChatRoom.toEntity(roomName, loginUser));
    }
}
