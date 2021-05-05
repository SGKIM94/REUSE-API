package reuse.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Category;

@Getter
@NoArgsConstructor
public class CreateCategoryResponseView {
    private Long id;
    private String teleco;
    private String manufacturer;
    private String model;

    @Builder
    public CreateCategoryResponseView(Long id, String teleco, String manufacturer, String model) {
        this.id = id;
        this.teleco = teleco;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public static CreateCategoryResponseView toDto(Category category) {
        return CreateCategoryResponseView.builder()
                .id(category.getId())
                .teleco(category.getTeleco())
                .manufacturer(category.getManufacturer())
                .model(category.getModel())
                .build();
    }
}
