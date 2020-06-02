package reuse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reuse.dto.category.CreateCategoryResponseView;
import reuse.dto.category.FindCategoryResponseView;
import reuse.repository.CategoryRepository;
import reuse.security.TokenAuthenticationService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.CategoryFixture.*;
import static reuse.fixture.CommonFixture.DEFAULT_ID;

@SpringBootTest
public class CategoryServiceTest {
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        categoryService = new CategoryService(categoryRepository);
    }

    @DisplayName("게시물이 생성되는지")
    @Test
    public void create() {
        when(categoryRepository.save(any())).thenReturn(TEST_CATEGORY);

        CreateCategoryResponseView category = categoryService.create(CREATE_CATEGORY_REQUEST_VIEW);

        assertThat(category.getId()).isNotNull();
    }

    @DisplayName("게시물이 조회가 되는지")
    @Test
    public void retrieve() {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(TEST_CATEGORY));

        FindCategoryResponseView category = categoryService.retrieve(DEFAULT_ID);

        assertThat(category.getTelco()).isEqualTo(TEST_TELECO);
        assertThat(category.getManufacturer()).isEqualTo(TEST_MANUFACTURER);
        assertThat(category.getModel()).isEqualTo(TEST_MODEL);
    }
}
