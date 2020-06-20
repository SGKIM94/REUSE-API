package reuse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.dto.category.CreateCategoryResponseView;
import reuse.dto.category.FindCategoryResponseView;
import reuse.repository.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static reuse.fixture.CategoryFixture.*;

public class CategoryServiceTest extends AbstractServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;


    @DisplayName("카테고리가 생성되는지")
    @Test
    public void create() {
        when(categoryRepository.save(any())).thenReturn(TEST_CATEGORY);

        CreateCategoryResponseView category = categoryService.create(CREATE_CATEGORY_REQUEST_VIEW);

        assertThat(category.getId()).isNotNull();
    }

    @DisplayName("카테고리가 조회가 되는지")
    @Test
    public void retrieve() {
        when(categoryRepository.findById(anyLong())).thenReturn(java.util.Optional.of(TEST_CATEGORY));

        FindCategoryResponseView category = categoryService.retrieve(DEFAULT_ID);

        assertThat(category.getTelco()).isEqualTo(TEST_TELECO);
        assertThat(category.getManufacturer()).isEqualTo(TEST_MANUFACTURER);
        assertThat(category.getModel()).isEqualTo(TEST_MODEL);
    }
}
