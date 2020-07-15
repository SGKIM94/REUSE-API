package reuse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.domain.BuyerReview;
import reuse.repository.SellerReviewRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.BuyerReviewFixture.CREATE_BUYER_REVIEW_REQUEST_VIEW;
import static reuse.fixture.BuyerReviewFixture.TEST_BUYER_REVIEW;
import static reuse.fixture.UserFixture.TEST_USER;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SellerReviewServiceTest extends AbstractServiceTest {
    @Mock
    private SellerReviewRepository sellerReviewRepository;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private SellerReviewService sellerReviewService;


    @DisplayName("판매자가 구매자에 대한 후기가 생성되는지")
    @Test
    public void create() {
        //given
        when(sellerReviewRepository.save(any())).thenReturn(TEST_BUYER_REVIEW);
        when(boardService.findById(any())).thenReturn(TEST_BOARD);

        //when
        BuyerReview buyerReview = sellerReviewService.create(CREATE_BUYER_REVIEW_REQUEST_VIEW, TEST_USER);

        // TODO: 해당 게시글을 조회해서 해당 게시글에 리뷰에 대한 key 가 저장되는지 확인 필요
        //then
        assertThat(buyerReview.getId()).isNotNull();
    }
}
