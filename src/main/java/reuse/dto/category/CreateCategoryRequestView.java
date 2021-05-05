package reuse.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Category;

@Getter
@NoArgsConstructor
public class CreateCategoryRequestView {
    private String teleco;
    private String manufacturer;
    private String model;
    private String deviceChange;

    @Builder
    public CreateCategoryRequestView(String teleco, String manufacturer, String model, String deviceChange) {
        this.teleco = teleco;
        this.manufacturer = manufacturer;
        this.model = model;
        this.deviceChange = deviceChange;
    }

    public Category toEntity() {
        return Category.builder()
                .teleco(teleco)
                .manufacturer(manufacturer)
                .model(model)
                .deviceChange(deviceChange)
                .build();
    }
}
