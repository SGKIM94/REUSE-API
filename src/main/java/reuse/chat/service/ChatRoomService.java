package reuse.chat.service;

import org.springframework.stereotype.Service;
import reuse.chat.domain.ChatRoom;
import reuse.chat.dto.ListChatRoomsResponseView;
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

    public ChatRoom findById(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId).orElseThrow(
                () -> new IllegalArgumentException("해당 채팅방이 존재하지 않습니다."));
    }

    public ChatRoom retrieve(Long chatRoomId) {
        return findById(chatRoomId);
    }

    public ListChatRoomsResponseView findAll() {
        return ListChatRoomsResponseView.toDto(chatRoomRepository.findAll());
    }
}
