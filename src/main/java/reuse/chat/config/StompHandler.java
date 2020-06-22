package reuse.chat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import reuse.exception.InvalidAccessTokenException;
import reuse.security.TokenAuthenticationService;

@Slf4j
@Component
public class StompHandler implements ChannelInterceptor {
    private final TokenAuthenticationService tokenAuthenticationService;

    public StompHandler(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (isInvalidAccessTokenWhenConnect(accessor.getCommand(), accessor.getFirstNativeHeader(HttpHeaders.AUTHORIZATION))) {
            throw new InvalidAccessTokenException("Not invalid access token when connect websocket!");
        }

        return message;
    }

    boolean isInvalidAccessTokenWhenConnect(StompCommand accessorStatus, String jwt) {
        return StompCommand.CONNECT == accessorStatus
                && tokenAuthenticationService.isVerifyToken(jwt);
    }
}
