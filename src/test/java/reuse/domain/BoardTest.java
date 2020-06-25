package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static reuse.fixture.BoardFixture.*;
import static reuse.fixture.BuyerReviewFixture.TEST_BUYER_REVIEW;

public class BoardTest {

    @DisplayName("게시물을 수정 가능한지")
    @Test
    public void modify() {
        //given
        Board board = TEST_BOARD;

        //when
        board.modify(MODIFY_BOARD_REQUEST_DTO);

        //then
        assertThat(board.getTitle()).isEqualTo(TEST_MODIFY_BOARD_TITLE);
    }

    @DisplayName("게시글이 삭제가 되는지")
    @Test
    public void delete() {
        //given
        Board board = TEST_BOARD;

        //when
        board.delete();

        //then
        assertThat(board.getIsDeleted()).isTrue();
    }

    @DisplayName("Board 에 BuyerReview 를 연결시키기위한 외래키를 저장하는지")
    @Test
    public void mappingBuyerReview() {
        //given
        BuyerReview buyerReview = TEST_BUYER_REVIEW;
        Board board = TEST_BOARD;

        //when
        board.mappingBuyerReview(buyerReview);

        //then
        assertThat(board.getBuyerReviewId()).isEqualTo(buyerReview.getId());
    }

    @DisplayName("Board 에 BuyerReview 를 연결시키기위한 외래키를 저장 시 존재하지 않을 경우 예외처리하는지")
    @Test
    public void mappingBuyerReviewFail() {
        //when
        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            TEST_BOARD.mappingBuyerReview(null);
        }).getMessage();

        assertThat(errorMessage).isEqualTo("Empty buyerReview when mapping to the Board!");
    }
}


