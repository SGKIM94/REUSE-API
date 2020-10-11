package reuse.chat.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.chat.dto.CreateChatRequestView;
import reuse.chat.service.ChatRoomService;
import reuse.domain.User;
import reuse.security.LoginUser;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatRoomService chatRoomService;

    public ChatController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateChatRequestView name, @LoginUser User loginUser) {
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
