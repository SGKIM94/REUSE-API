package reuse.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.dto.review.buyer.ListBuyerReviewRequestView;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.CommonFixture.DEFAULT_ID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BuyerReviewRepositoryTest {
    @Autowired
    private BuyerReviewRepository buyerReviewRepository;

    @DisplayName("판매자에 연결되어 있는 게시글 후기를 모두 가져오는지")
    @Test
    @Sql(scripts = {"/before-favorite-board.sql", "/insert-buyer-review.sql"})
    public void findBySeller() {
        //when
        ListBuyerReviewRequestView reviews = buyerReviewRepository.findBySeller(DEFAULT_ID);

        assertThat(reviews.getSize()).isEqualTo(3);
    }
}
