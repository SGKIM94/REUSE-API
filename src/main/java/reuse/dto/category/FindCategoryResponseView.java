package reuse.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Category;

@Getter
@Setter
@NoArgsConstructor
public class FindCategoryResponseView {
    private Long id;
    private String telco;
    private String manufacturer;
    private String model;

    @Builder
    public FindCategoryResponseView(Long id, String telco, String manufacturer, String model) {
        this.id = id;
        this.telco = telco;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public static FindCategoryResponseView toDto(Category category) {
        return FindCategoryResponseView.builder()
                .id(category.getId())
                .manufacturer(category.getManufacturer())
                .model(category.getModel())
                .telco(category.getTeleco())
                .build();
    }
}
