package reuse.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.domain.BuyerReview;
import reuse.domain.User;
import reuse.dto.review.buyer.ListBuyerReviewResponseView;
import reuse.repository.BuyerReviewRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.BoardFixture.TEST_FIRST_BOARD_ID;
import static reuse.fixture.BuyerReviewFixture.*;
import static reuse.fixture.CommonFixture.DEFAULT_ID;
import static reuse.fixture.UserFixture.TEST_USER;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BuyerReviewServiceTest extends AbstractServiceTest {
    @Mock
    private BuyerReviewRepository buyerReviewRepository;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private BuyerReviewService buyerReviewService;


    @DisplayName("게시물에 대한 후기가 생성되는지")
    @Test
    public void create() {
        //given
        User buyer = TEST_USER;

        when(buyerReviewRepository.save(any())).thenReturn(TEST_BUYER_REVIEW);
        when(boardService.findById(any())).thenReturn(TEST_BOARD);

        //when
        BuyerReview buyerReview = buyerReviewService.create(CREATE_BUYER_REVIEW_REQUEST_VIEW, buyer);

        //then
        assertThat(buyer.getScore()).isEqualTo(10);
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

    @DisplayName("구매후기를 해당 ID 로 조회 가능한지")
    @Test
    @Order(1)
    public void retrieve() {
        //given
        when(buyerReviewRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_BUYER_REVIEW));

        //when
        BuyerReview buyerReview = buyerReviewService.retrieve(DEFAULT_ID);

        //then
        assertThat(buyerReview.getContent()).isEqualTo(TEST_CONTENT);
        assertThat(buyerReview.getScore()).isEqualTo(TEST_SCORE);
        assertThat(buyerReview.getTitle()).isEqualTo(TEST_TITLE);
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

    @DisplayName("구매후기를 수정 가능한지")
    @Test
    @Order(2)
    public void modify() {
        //given
        when(buyerReviewRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_BUYER_REVIEW));

        BuyerReview modifiedBuyerReview = buyerReviewService.modify(TEST_SECOND_BUYER_REVIEW, DEFAULT_ID);

        assertThat(modifiedBuyerReview.getTitle()).isEqualTo(TEST_SECOND_TITLE);
        assertThat(modifiedBuyerReview.getContent()).isEqualTo(TEST_SECOND_CONTENT);
        assertThat(modifiedBuyerReview.getScore()).isEqualTo(TEST_SECOND_RATING);
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

    @DisplayName("구매후기를 작성하는 사용자와 게시글의 구매자와 같지 않은 경우 예외를 처리하는지")
    @Test
    public void verifyBuyer() {
        //given
        when(boardService.findById(any())).thenReturn(TEST_BOARD);

        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            buyerReviewService.create(getCreateBuyerReviewRequestView(TEST_FIRST_BOARD_ID), TEST_USER);
        }).getMessage();

        //then
        assertThat(errorMessage).isEqualTo("해당 게시글의 구매자와 로그인 사용자와 일치하지 않습니다.");
    }

    @DisplayName("구매후기를 작성하는 사용자와 게시글이 거래 완료 상태가 아닌 경우 예외를 처리하는지")
    @Test
    public void verifySalesStatus() {
        //when
        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            buyerReviewService.create(getCreateBuyerReviewRequestView(TEST_FIRST_BOARD_ID), TEST_USER);
        }).getMessage();

        //then
        assertThat(errorMessage).isEqualTo("거래 완료 상태가 아니기 때문에 후기를 작성할 수 없습니다. : BOOKING");
    }
}
