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
import static reuse.fixture.BoardFixture.TEST_SECOND_BOARD_ID;
import static reuse.fixture.ProductImagesFixture.FIRST_IMAGE_URL;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FavoriteBoardRepositoryTest {
    @Autowired
    private FavoriteBoardRepository favoriteBoardRepository;

    @DisplayName("특정 사용자가 등록한 즐겨찾기 게시판을 모두 조회 가능한지")
    @Test
    @Sql(scripts = "/before-favorite-board.sql")
    public void findAllByCategory() {
        //when
        ListBoardWithProductResponseView boards = favoriteBoardRepository.findAllByUserId(1L);
        int size = boards.getSize();
        FindWithProductResponseView firstBoard = boards.getFirstIndex();
        FindWithProductResponseView secondBoard = boards.getSecondIndex();

        //then
        assertThat(size).isEqualTo(4);
        assertThat(firstBoard.getId()).isEqualTo(TEST_FIRST_BOARD_ID);
        assertThat(firstBoard.getMainImage()).isEqualTo(FIRST_IMAGE_URL);
        assertThat(secondBoard.getId()).isEqualTo(TEST_SECOND_BOARD_ID);
        assertThat(secondBoard.getMainImage()).isEqualTo(FIRST_IMAGE_URL);
    }
}
