package reuse.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class EmbeddedRedisConfig {
    private RedisServer redisServer;

    @Value("${spring.redis.port}")
    private int redisPort;

//    @PostConstruct
//    public void redisServer() {
//        redisServer = new RedisServer(redisPort);
//        redisServer.start();
//    }
//
//    @PreDestroy
//    public void stopRedis() {
//        if (redisServer != null) {
//            redisServer.stop();
//        }
//    }
}
