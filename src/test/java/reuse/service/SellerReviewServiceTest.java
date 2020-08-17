package reuse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.domain.SellerReview;
import reuse.repository.SellerReviewRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.BoardFixture.TEST_SECOND_BOARD;
import static reuse.fixture.SellerReviewFixture.CREATE_SELLER_REVIEW_REQUEST_VIEW;
import static reuse.fixture.SellerReviewFixture.TEST_SELLER_REVIEW;
import static reuse.fixture.UserFixture.TEST_SECOND_USER;
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
        when(boardService.findById(any())).thenReturn(TEST_SECOND_BOARD);
        when(sellerReviewRepository.save(any())).thenReturn(TEST_SELLER_REVIEW);

        //when
        SellerReview sellerReview = sellerReviewService.create(CREATE_SELLER_REVIEW_REQUEST_VIEW, TEST_USER);

        //then
        assertThat(sellerReview.getScore()).isEqualTo(10);
        assertThat(sellerReview.getId()).isNotNull();
    }

    @DisplayName("게시물 생성 시 게시글 판매자와 후기 요청자의 아이디가 다른 경우 예외를 처리하는지")
    @Test
    public void createFailWhenDifferenceRequestAndSeller() {
        //given
        when(boardService.findById(any())).thenReturn(TEST_SECOND_BOARD);

        //when
        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            sellerReviewService.create(CREATE_SELLER_REVIEW_REQUEST_VIEW, TEST_SECOND_USER);
        }).getMessage();

        //then
        assertThat(errorMessage).isEqualTo("판매자와 예약 신청한 사용자가 다릅니다.");
    }

    @DisplayName("게시물 생성 시 게시글의 상태가 거래완료 상태가 아닌 경우 예외를 처리하는지")
    @Test
    public void createFailWhenNotCompleteStatus() {
        //given
        when(boardService.findById(any())).thenReturn(TEST_BOARD);

        //when
        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            sellerReviewService.create(CREATE_SELLER_REVIEW_REQUEST_VIEW, TEST_USER);
        }).getMessage();

        //then
        assertThat(errorMessage).isEqualTo("현재 상태 :  RESERVE | 요구되는 상태 COMPLETE");
    }
}
