package reuse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.domain.BuyerReview;
import reuse.dto.review.buyer.ListBuyerReviewResponseView;
import reuse.repository.BuyerReviewRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.BuyerReviewFixture.*;
import static reuse.fixture.CommonFixture.DEFAULT_ID;
import static reuse.fixture.UserFixture.TEST_USER;

public class BuyerReviewServiceTest extends AbstractServiceTest {
    @Mock
    private BuyerReviewRepository buyerReviewRepository;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private BuyerReviewService buyerReviewService;


    @DisplayName("게시물이 생성되는지")
    @Test
    public void create() {
        //given
        when(buyerReviewRepository.save(any())).thenReturn(TEST_BUYER_REVIEW);
        when(boardService.findById(any())).thenReturn(TEST_BOARD);

        //when
        BuyerReview buyerReview = buyerReviewService.create(CREATE_BUYER_REVIEW_REQUEST_VIEW, TEST_USER);

        // TODO: 해당 게시글을 조회해서 해당 게시글에 리뷰에 대한 key 가 저장되는지 확인 필요
        //then
        assertThat(buyerReview.getId()).isNotNull();
    }

    @DisplayName("판매자에 연결되어 있는 게시글 후기를 모두 가져오는지")
    @Test
    public void findBySeller() {
        //given
        when(buyerReviewRepository.findBySeller(any())).thenReturn(LIST_BUYER_REVIEW_REQUEST_VIEW);

        //when
        ListBuyerReviewResponseView buyerReviews = buyerReviewService.findBySeller(DEFAULT_ID);

        //then
        assertThat(buyerReviews.getSize()).isEqualTo(2);
    }

    @DisplayName("모든 구매후기를 조회 가능한지")
    @Test
    public void list() {
        //given
        when(buyerReviewRepository.findAll()).thenReturn(TEST_BUYER_REVIEWS);

        //when
        ListBuyerReviewResponseView buyerReviews = buyerReviewService.list();

        //then
        assertThat(buyerReviews.getSize()).isEqualTo(3);
    }

    @DisplayName("구매후기를 저장 가능한지")
    @Test
    public void modify() {
        buyerReviewService.modify(TEST_BUYER_REVIEW);
        //TODO : 검증 필요
    }

    @DisplayName("구매후기를 해당 ID 로 조회 가능한지")
    @Test
    public void retrieve() {
        //given
        when(buyerReviewRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_BUYER_REVIEW));

        //when
        BuyerReview buyerReview = buyerReviewService.retrieve(DEFAULT_ID);

        //then
        assertThat(buyerReview.getContent()).isEqualTo(TEST_CONTENT);
        assertThat(buyerReview.getRating()).isEqualTo(TEST_RATING);
        assertThat(buyerReview.getTitle()).isEqualTo(TEST_TITLE);
    }

    @DisplayName("구매후기가 존재하지 않는 것을 조회 시 예외를 처리하는지")
    @Test
    public void retrieveFail() {
        //when
        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            buyerReviewService.retrieve(DEFAULT_ID);
        }).getMessage();

        //then
        assertThat(errorMessage).isEqualTo("해당 구매후기가 존재하지 않습니다. : " + DEFAULT_ID);
    }
}
