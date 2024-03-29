package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static reuse.fixture.BuyerReviewFixture.*;

public class BuyerReviewTest {

    @DisplayName("게시물을 수정 가능한지")
    @Test
    public void modify() {
        //given
        BuyerReview buyerReview = TEST_BUYER_REVIEW;

        //when
        buyerReview.modify(TEST_SECOND_BUYER_REVIEW);

        //then
        assertAll(
                () -> assertThat(buyerReview.getContent()).isEqualTo(TEST_SECOND_CONTENT),
                () -> assertThat(buyerReview.getScore()).isEqualTo(TEST_SECOND_SCORE),
                () -> assertThat(buyerReview.getTitle()).isEqualTo(TEST_SECOND_TITLE)
        );
    }
}


