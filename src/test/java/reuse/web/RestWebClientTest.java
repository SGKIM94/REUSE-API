package reuse.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class RestWebClientTest {
    private static final String NO_AUTHORIZATION = "";
    private WebTestClient webTestClient;

    public RestWebClientTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    <T> EntityExchangeResult<T> postMethodAcceptance(String uri, Object requestBody, Class<T> responseBodyClass) {
        return postMethodWithAuthAcceptance(uri, requestBody, responseBodyClass, NO_AUTHORIZATION);
    }

    <T> EntityExchangeResult<T> getMethodAcceptance(String uri, Class<T> responseBodyClass) {
        return getMethodWithAuthAcceptance(uri, responseBodyClass, NO_AUTHORIZATION);
    }

    <T> EntityExchangeResult<T> getMethodWithAuthAcceptance(String uri, Class<T> responseBodyClass, String jwt) {
        return this.webTestClient.get().uri(uri)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(responseBodyClass)
                .returnResult();
    }

    public <T> EntityExchangeResult<T> postMethodWithAuthAcceptance(String uri, Object requestBody, Class<T> responseBodyClass, String jwt) {
        return webTestClient.post().uri(uri)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), requestBody.getClass())
                .exchange()
                .expectBody(responseBodyClass)
                .returnResult();
    }

    <T> EntityExchangeResult<T> postMethodWithFormData(String uri, Object requestBody, Class<T> responseBodyClass, String jwt) {
        return webTestClient.post().uri(uri)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(Mono.just(requestBody), requestBody.getClass())
                .exchange()
                .expectBody(responseBodyClass)
                .returnResult();
    }

    <T> WebTestClient.BodyContentSpec getMethodWithAuthAcceptance(String uri, String jwt) {
        return this.webTestClient.get().uri(uri)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody();
    }


    <T> WebTestClient.BodyContentSpec postMethodWithAuthAcceptance(String uri, Object requestBody, String jwt) {
        return webTestClient.post().uri(uri)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), requestBody.getClass())
                .exchange()
                .expectBody();
    }

    <T> EntityExchangeResult<Void> deleteMethodWithAuthAcceptance(String uri, String jwt) {
        return this.webTestClient.delete().uri(uri)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .exchange()
                .expectBody(Void.class)
                .returnResult();
    }

    <T> EntityExchangeResult<T> putMethodWithAuthAcceptance(String uri, Object requestBody, Class<T> responseBodyClass, String jwt) {
        return webTestClient.put().uri(uri)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestBody), requestBody.getClass())
                .exchange()
                .expectBody(responseBodyClass)
                .returnResult();
    }

}
