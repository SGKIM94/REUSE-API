package reuse.chat.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import reuse.chat.dto.ListChatMessageResponseView;
import reuse.chat.dto.PublishChatRequestView;
import reuse.chat.service.ChatMessageService;
import reuse.domain.User;
import reuse.security.LoginUser;

@Slf4j
@RestController
public class WebSocketController {
    private final ChatMessageService chatMessageService;

    public WebSocketController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/message")
    @SendTo("/pub/message")
    public ResponseEntity message(PublishChatRequestView message, @LoginUser User sender) {
        log.info("메시지를 보냈습니다.");
        log.info("message: " + message);

        chatMessageService.createByPublishChatDto(message, sender);
        ListChatMessageResponseView messages = chatMessageService.findByChatRoomId(message.getRoomId());
        chatMessageService.publishMessage(message, messages);

        return ResponseEntity.ok().build();
    }
}

