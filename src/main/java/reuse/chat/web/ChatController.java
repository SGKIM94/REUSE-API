package reuse.chat.web;


import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import reuse.chat.domain.ChatMessage;
import reuse.chat.service.ChatMessageService;
import reuse.chat.service.ChatRoomService;
import reuse.domain.User;
import reuse.security.LoginUser;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    public ChatController(ChatRoomService chatRoomService, ChatMessageService chatMessageService) {
        this.chatRoomService = chatRoomService;
        this.chatMessageService = chatMessageService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody String name, @LoginUser User loginUser) {
        return ResponseEntity.ok().body(chatRoomService.create(name, loginUser));
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(chatRoomService.findById(id));
    }

    @GetMapping()
    public ResponseEntity list() {
        return ResponseEntity.ok().body(chatRoomService.findAll());
    }

    @MessageMapping("/message")
    public void message(ChatMessage message) {
    }
}
