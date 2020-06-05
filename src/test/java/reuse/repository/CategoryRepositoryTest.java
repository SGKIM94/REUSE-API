package reuse.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static reuse.fixture.BoardFixture.TEST_BOARDS;
import static reuse.fixture.CategoryFixture.TEST_CATEGORY;
import static reuse.fixture.CategoryFixture.TEST_LIST_CATEGORY;

@ExtendWith(SpringExtension.class)
@DataJdbcTest
public class CategoryRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BoardRepositorySupport boardRepositorySupport;

    @AfterEach
    void setUp() {
        boardRepository.deleteAllInBatch();
    }

    @DisplayName("특정 카테고리를 가진 품목을 조회하는지")
    @Test
    public void findAllByCategory() {
        categoryRepository.saveAll(TEST_LIST_CATEGORY);
        //TODO: 해당 categoryId 를 가진 품목을 가지는 Board fixture 추가 필요
        boardRepository.saveAll(TEST_BOARDS);

        boardRepositorySupport.findAllByCategory(TEST_CATEGORY);
    }
}
