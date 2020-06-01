package reuse.fixture;

import reuse.domain.Category;
import reuse.dto.category.CreateCategoryRequestView;

public class CategoryFixture extends CommonFixture {
    public static final String TEST_TELECO = "1";
    public static final String TEST_MANUFACTURER = "1";
    public static final String TEST_MODEL = "APPLE";

    public static final CreateCategoryRequestView CREATE_CATEGORY_REQUEST_VIEW = CreateCategoryRequestView.builder()
            .teleco("0").manufacturer("0").model("0").build();

    public static final Category TEST_CATEGORY
            = Category.builder().teleco(TEST_TELECO).manufacturer(TEST_MANUFACTURER).model(TEST_MODEL).build();
}
