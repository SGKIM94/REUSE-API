package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.dto.review.buyer.CreateBuyerReviewRequestView;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BuyerReviewFixture.CREATE_BUYER_REVIEW_REQUEST_VIEW;

public class BuyerReviewAcceptanceTest extends AbstractAcceptanceTest {
    public static final String BUYER_REVIEW_BASE_URL = "/review/buyer";

    private CreateWebClientTest createWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private String socialTokenId;

    @BeforeEach
    void setUp() {
        this.createWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
        socialTokenId = createWebClientTest.createUser();
    }

    @DisplayName("구매자가 구매한 게시판에 구매평을 남기는지")
    @Test
    public void createBuyerReview() {
        //when
        EntityExchangeResult<CreateBuyerReviewRequestView> expectResponse = createWebClientTest.postMethodWithAuthAcceptance
                (BUYER_REVIEW_BASE_URL, CREATE_BUYER_REVIEW_REQUEST_VIEW, CreateBuyerReviewRequestView.class, getJwt());

        HttpStatus status = expectResponse.getStatus();

        assertThat(status).isEqualTo(HttpStatus.OK);
    }

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
    }
}
