package reuse.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.dto.board.FindAllByCategoryResponseView;
import reuse.dto.board.FindAllByCategoryResponseViews;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.CategoryFixture.TEST_CATEGORY;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BoardRepositorySupport boardRepositorySupport;

    @AfterEach
    void setUp() {
//        boardRepository.deleteAllInBatch();
    }

    @DisplayName("특정 카테고리를 가진 품목을 조회하는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-users.sql", "/insert-categories.sql",
            "/insert-products.sql", "/insert-boards.sql"})
    public void findAllByCategory() {
        //when
        FindAllByCategoryResponseViews boards = boardRepositorySupport.findAllByCategory(TEST_CATEGORY);
        FindAllByCategoryResponseView firstBoard = boards.getFirstIndex();
        FindAllByCategoryResponseView secondBoard = boards.getSecondIndex();

        //then
        assertThat(firstBoard.getContent()).isEqualTo("테스트 게시판1");
        assertThat(secondBoard.getContent()).isEqualTo("테스트 게시판6");
    }
}
