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
            = new Category(1L, TEST_TELECO, TEST_MANUFACTURER, TEST_MODEL);

    public static final Category TEST_SECOND_CATEGORY
            = new Category(2L, TEST_SECOND_TELECO, TEST_SECOND_MANUFACTURER, TEST_SECOND_MODEL);

    public static final Category TEST_THIRD_CATEGORY
            = new Category(3L, TEST_THIRD_TELECO, TEST_THIRD_MANUFACTURER, TEST_THIRD_MODEL);

    public static final Category TEST_FOURTH_CATEGORY
            = new Category(4L, TEST_FOURTH_TELECO, TEST_FOURTH_MANUFACTURER, TEST_FOURTH_MODEL);

    public static final Category TEST_FIFTH_CATEGORY
            = new Category(5L, TEST_FIFTH_TELECO, TEST_FIFTH_MANUFACTURER, TEST_FIFTH_MODEL);

    public static final List<Category> TEST_LIST_CATEGORY
            = Arrays.asList(TEST_CATEGORY, TEST_SECOND_CATEGORY, TEST_THIRD_CATEGORY, TEST_FOURTH_CATEGORY);
}
