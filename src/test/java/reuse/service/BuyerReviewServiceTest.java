package reuse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.repository.BuyerReviewRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;
import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.UserFixture.TEST_USER;

public class BuyerReviewServiceTest extends AbstractServiceTest {
    @Mock
    private BuyerReviewRepository buyerReviewRepository;

    @InjectMocks
    private BuyerReviewService buyerReviewService;


    @DisplayName("게시물이 생성되는지")
    @Test
    public void create() {
        when(buyerReviewRepository.save(any())).thenReturn(TEST_BOARD);

        buyerReviewService.create(CREATE_BOARD_REQUEST_VIEW, TEST_USER);

        assertThat(board.getId()).isNotNull();
    }
}
