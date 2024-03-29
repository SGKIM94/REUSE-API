package reuse.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Category;

@Getter
@NoArgsConstructor
public class FindCategoryResponseView {
    private Long id;
    private String telco;
    private String manufacturer;
    private String model;
    private String deviceChange;

    @Builder
    public FindCategoryResponseView(Long id, String telco, String manufacturer, String model, String deviceChange) {
        this.id = id;
        this.telco = telco;
        this.manufacturer = manufacturer;
        this.model = model;
        this.deviceChange = deviceChange;
    }

    public static FindCategoryResponseView toDto(Category category) {
        return FindCategoryResponseView.builder()
                .id(category.getId())
                .manufacturer(category.getManufacturer())
                .model(category.getModel())
                .telco(category.getTeleco())
                .deviceChange(category.getDeviceChange())
                .build();
    }
}
