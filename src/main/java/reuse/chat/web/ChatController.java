package reuse.chat.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.chat.service.ChatRoomService;
import reuse.domain.User;
import reuse.security.LoginUser;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private ChatRoomService chatRoomService;

    public ChatController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
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
}
