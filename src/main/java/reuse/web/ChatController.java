package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reuse.domain.User;
import reuse.security.LoginUser;
import reuse.service.ChatRoomService;

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
}
