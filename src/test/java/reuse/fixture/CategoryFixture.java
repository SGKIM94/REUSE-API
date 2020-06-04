package reuse.fixture;

import reuse.domain.Category;
import reuse.dto.category.CreateCategoryRequestView;

import java.util.Arrays;
import java.util.List;

public class CategoryFixture extends CommonFixture {
    public static final String TEST_TELECO = "1";
    public static final String TEST_MANUFACTURER = "1";
    public static final String TEST_MODEL = "1";

    public static final String TEST_SECOND_TELECO = "2";
    public static final String TEST_SECOND_MODEL = "2";
    public static final String TEST_SECOND_MANUFACTURER = "2";

    public static final String TEST_THIRD_TELECO = "3";
    public static final String TEST_THIRD_MANUFACTURER = "3";
    public static final String TEST_THIRD_MODEL = "3";

    public static final String TEST_FOURTH_TELECO = "4";
    public static final String TEST_FOURTH_MANUFACTURER = "4";
    public static final String TEST_FOURTH_MODEL = "4";

    public static final String TEST_FIFTH_TELECO = "1";
    public static final String TEST_FIFTH_MANUFACTURER = "1";
    public static final String TEST_FIFTH_MODEL = "1";

    public static final CreateCategoryRequestView CREATE_CATEGORY_REQUEST_VIEW = CreateCategoryRequestView.builder()
            .teleco(TEST_TELECO).manufacturer(TEST_MANUFACTURER).model(TEST_MODEL).build();

    public static final Category TEST_CATEGORY
            = Category.builder().teleco(TEST_TELECO).manufacturer(TEST_MANUFACTURER).model(TEST_MODEL).build();

    public static final Category TEST_SECOND_CATEGORY
            = Category.builder().teleco(TEST_SECOND_TELECO).manufacturer(TEST_SECOND_MANUFACTURER).model(TEST_SECOND_MODEL).build();

    public static final Category TEST_THIRD_CATEGORY
            = Category.builder().teleco(TEST_THIRD_TELECO).manufacturer(TEST_THIRD_MANUFACTURER).model(TEST_THIRD_MODEL).build();

    public static final Category TEST_FOURTH_CATEGORY
            = Category.builder().teleco(TEST_FOURTH_TELECO).manufacturer(TEST_FOURTH_MANUFACTURER).model(TEST_FOURTH_MODEL).build();

    public static final Category TEST_FIFTH_CATEGORY
            = Category.builder().teleco(TEST_FIFTH_TELECO).manufacturer(TEST_FIFTH_MANUFACTURER).model(TEST_FIFTH_MODEL).build();

    public static final List<Category> TEST_LIST_CATEGORY
            = Arrays.asList(TEST_CATEGORY, TEST_SECOND_CATEGORY, TEST_THIRD_CATEGORY, TEST_FOURTH_CATEGORY);
}
