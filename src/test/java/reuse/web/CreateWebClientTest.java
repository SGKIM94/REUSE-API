package reuse.web;

import org.springframework.test.web.reactive.server.WebTestClient;
import reuse.domain.User;

import java.util.Objects;

import static reuse.web.UserAcceptanceTest.KIM_INPUT_JSON;
import static reuse.web.UserAcceptanceTest.USER_BASE_URL;

public class CreateWebClientTest extends RestWebClientTest {

    public CreateWebClientTest(WebTestClient webTestClient) {
        super(webTestClient);
    }

    String createUser() {
        return Objects.requireNonNull(
                postMethodAcceptance(USER_BASE_URL + "/sigh-up", KIM_INPUT_JSON, User.class)
                .getResponseHeaders()
                .getLocation()
                .getPath());
    }
}
