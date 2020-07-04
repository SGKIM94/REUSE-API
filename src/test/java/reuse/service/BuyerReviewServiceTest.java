package reuse.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.domain.Board;
import reuse.domain.BuyerReview;
import reuse.dto.review.buyer.ListBuyerReviewResponseView;
import reuse.repository.BuyerReviewRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.*;
import static reuse.fixture.BuyerReviewFixture.*;
import static reuse.fixture.CommonFixture.DEFAULT_ID;
import static reuse.fixture.UserFixture.TEST_SECOND_USER;
import static reuse.fixture.UserFixture.TEST_USER;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        assertThat(buyerReview.getRating()).isEqualTo(TEST_RATING);
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
        assertThat(modifiedBuyerReview.getRating()).isEqualTo(TEST_SECOND_RATING);
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
            buyerReviewService.verifyBuyer(getCreateBuyerReviewRequestView(TEST_FIRST_BOARD_ID), TEST_USER);
        }).getMessage();

        //then
        assertThat(errorMessage).isEqualTo("해당 게시글의 구매자와 로그인 사용자와 일치하지 않습니다.");
    }

    @DisplayName("구매후기를 작성하는 사용자와 게시글의 구매자와 같은 경우 해당 게시글을 리턴하는지")
    @Test
    public void verifyBuyerThenReturnBoard() {
        //given
        when(boardService.findById(any())).thenReturn(TEST_BOARD);

        Board board = buyerReviewService.verifyBuyer(CREATE_BUYER_REVIEW_REQUEST_VIEW, TEST_SECOND_USER);

        //then
        assertThat(board.getBuyerId()).isEqualTo(SECOND_ID);
    }

    @DisplayName("구매후기를 작성하는 사용자와 게시글이 거래 완료 상태가 아닌 경우 예외를 처리하는지")
    @Test
    public void verifySalesStatus() {
        //when
        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            buyerReviewService.verifyBoardStatus(TEST_BOARD);
        }).getMessage();

        //then
        assertThat(errorMessage).isEqualTo("거래 완료 상태가 아니기 때문에 후기를 작성할 수 없습니다. : BOOKING");
    }

    @DisplayName("구매후기를 작성하는 사용자와 게시글이 거래 완료이여서 작성이 가능한지")
    @Test
    public void verifySalesStatusWhenComplete() {
        //when
        buyerReviewService.verifyBoardStatus(TEST_SECOND_BOARD);
    }
}
