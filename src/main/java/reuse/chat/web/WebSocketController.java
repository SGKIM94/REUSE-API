package reuse.chat.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import reuse.chat.dto.PublishChatRequestView;
import reuse.chat.service.ChatMessageService;

@Slf4j
@RestController
public class WebSocketController {
    private final ChatMessageService chatMessageService;

    public WebSocketController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/message")
    @SendTo("/pub/message")
    public ResponseEntity message(PublishChatRequestView message) {
        log.info("메시지를 보냈습니다.");
        log.info("message: " + message);

        chatMessageService.publishMessage(message);
        return ResponseEntity.ok().build();
    }
}

