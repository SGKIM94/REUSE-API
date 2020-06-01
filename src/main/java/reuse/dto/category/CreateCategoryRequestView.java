package reuse.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Category;

@Getter
@Setter
@NoArgsConstructor
public class CreateCategoryRequestView {
    private String teleco;
    private String manufacturer;
    private String model;

    @Builder
    public CreateCategoryRequestView(String teleco, String manufacturer, String model) {
        this.teleco = teleco;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Category toEntity() {
        return Category.builder()
                .teleco(teleco)
                .manufacturer(manufacturer)
                .model(model)
                .build();
    }
}
