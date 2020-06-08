package reuse.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.dto.board.FindByCategoryResponseView;
import reuse.dto.board.ListBoardByCategoryResponseView;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.FIRST_BOARD_ID;
import static reuse.fixture.BoardFixture.SIXTH_BOARD_ID;
import static reuse.fixture.CategoryFixture.TEST_CATEGORY;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @AfterEach
    void setUp() {
    }

    @DisplayName("특정 카테고리를 가진 품목을 조회하는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-users.sql", "/insert-categories.sql",
            "/insert-products.sql", "/insert-boards.sql"})
    public void findAllByCategory() {
        //when
        ListBoardByCategoryResponseView boards = boardRepository.findAllByCategory(TEST_CATEGORY);
        FindByCategoryResponseView firstBoard = boards.getFirstIndex();
        FindByCategoryResponseView secondBoard = boards.getSecondIndex();

        //then
        assertThat(firstBoard.getId()).isEqualTo(FIRST_BOARD_ID);
        assertThat(secondBoard.getId()).isEqualTo(SIXTH_BOARD_ID);
    }
}
