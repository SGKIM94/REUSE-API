package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BuyerReviewFixture.TEST_BUYER_REVIEW;

public class BuyerReviewTest {

    @DisplayName("게시물을 수정 가능한지")
    @Test
    public void modify() {
        //given
        BuyerReview buyerReview = TEST_BUYER_REVIEW;

        //when
        buyerReview.modify(new BuyerReview());

        //then
        assertThat(buyerReview.getContent()).isEqualTo("변경된 내용");
    }
}


