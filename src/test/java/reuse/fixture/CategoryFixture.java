package reuse.fixture;

import reuse.dto.category.CreateCategoryRequestView;

public class CategoryFixture extends CommonFixture {
    public static final CreateCategoryRequestView CREATE_CATEGORY_REQUEST_VIEW = CreateCategoryRequestView.builder()
            .teleco("0").manufacturer("0").model("0").build();
}
