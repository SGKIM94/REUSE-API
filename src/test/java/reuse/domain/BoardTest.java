package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.*;

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
}


