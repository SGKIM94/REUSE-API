package reuse.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.domain.Board;
import reuse.domain.SalesStatusType;
import reuse.dto.board.FindWithProductResponseView;
import reuse.dto.board.ListBoardWithProductResponseView;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.*;
import static reuse.fixture.CategoryFixture.TEST_CATEGORY;
import static reuse.fixture.ProductImagesFixture.FIRST_IMAGE_URL;

@Disabled
// 별도로 돌렸을 때는 문제되지 않지만 한번에 빌드했을 때 오류 발생
// 고립이나 의존성 분리에 고민 필요
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
        assertThat(firstBoard.getMainImage()).isEqualTo(FIRST_IMAGE_URL);
        assertThat(secondBoard.getId()).isEqualTo(TEST_SIXTH_BOARD_ID);
        assertThat(secondBoard.getMainImage()).isEqualTo(FIRST_IMAGE_URL);
    }


    @DisplayName("게시글 생성이 되는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql", "/insert-users.sql"})
    public void create() {
        //when
        Board savedBoard = boardRepository.save(TEST_BOARD);

        //then
        assertThat(savedBoard.getId()).isNotNull();
        assertThat(savedBoard.getSalesStatus()).isEqualTo(SalesStatusType.SALE);
    }

    @DisplayName("게시글 조회 시 SalesStatusType 이 정상적으로 조회되는지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql", "/insert-users.sql"})
    public void findAll() {
        //given
        boardRepository.save(TEST_BOARD);

        //when
        List<Board> boards = boardRepository.findAll();
        Board board = boards.get(0);

        //then
        assertThat(board.getSalesStatus()).isEqualTo(SalesStatusType.SALE);
    }
}
