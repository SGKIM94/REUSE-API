package reuse.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.dto.board.FindWithProductResponseView;
import reuse.dto.board.ListBoardWithProductResponseView;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.TEST_FIRST_BOARD_ID;
import static reuse.fixture.BoardFixture.TEST_SIXTH_BOARD_ID;
import static reuse.fixture.CategoryFixture.TEST_CATEGORY;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @DisplayName("특정 카테고리를 가진 품목을 조회하는지")
    @Test
    @Sql(scripts = "/before-favorite-board.sql")
    public void findAllByCategory() {
        //when
        ListBoardWithProductResponseView boards = boardRepository.findAllByCategory(TEST_CATEGORY);
        FindWithProductResponseView firstBoard = boards.getFirstIndex();
        FindWithProductResponseView secondBoard = boards.getSecondIndex();

        //then
        assertThat(firstBoard.getId()).isEqualTo(TEST_FIRST_BOARD_ID);
        assertThat(secondBoard.getId()).isEqualTo(TEST_SIXTH_BOARD_ID);
    }
}
