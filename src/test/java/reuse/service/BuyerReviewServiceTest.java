package reuse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.domain.BuyerReview;
import reuse.repository.BuyerReviewRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.BuyerReviewFixture.CREATE_BUYER_REVIEW_REQUEST_VIEW;
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
        when(buyerReviewRepository.save(any())).thenReturn(CREATE_BUYER_REVIEW_REQUEST_VIEW);
        when(boardService.findById(any())).thenReturn(TEST_BOARD);

        BuyerReview buyerReview = buyerReviewService.create(CREATE_BUYER_REVIEW_REQUEST_VIEW, TEST_USER);

        // TODO: 해당 게시글을 조회해서 해당 게시글에 리뷰에 대한 key 가 저장되는지 확인 필요
        assertThat(buyerReview.getId()).isNotNull();
    }
}
