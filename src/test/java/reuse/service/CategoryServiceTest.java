package reuse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reuse.domain.Category;
import reuse.dto.category.CreateCategoryResponseView;
import reuse.dto.category.FindCategoryResponseView;
import reuse.repository.CategoryRepository;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.CategoryFixture.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryServiceTest {
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        categoryService = new CategoryService(categoryRepository);
    }

    @DisplayName("카테고리가 생성되는지")
    @Test
    public void create() {
        categoryRepository.save(TEST_CATEGORY);

        CreateCategoryResponseView category = categoryService.create(CREATE_CATEGORY_REQUEST_VIEW);

        assertThat(category.getId()).isNotNull();
    }

    @DisplayName("카테고리가 조회가 되는지")
    @Test
    public void retrieve() {
        Category savedCategory = categoryRepository.save(TEST_CATEGORY);

        FindCategoryResponseView category = categoryService.retrieve(savedCategory.getId());

        assertThat(category.getTelco()).isEqualTo(TEST_TELECO);
        assertThat(category.getManufacturer()).isEqualTo(TEST_MANUFACTURER);
        assertThat(category.getModel()).isEqualTo(TEST_MODEL);
    }
}
