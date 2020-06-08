package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Category;

@Getter
@Setter
@NoArgsConstructor
public class ListBoardByCategoryRequestView {
    private String teleco;
    private String manufacturer;
    private String model;

    @Builder
    public ListBoardByCategoryRequestView(String teleco, String manufacturer, String model) {
        this.teleco = teleco;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Category toEntity() {
        return Category.builder()
                .manufacturer(manufacturer)
                .model(model)
                .teleco(teleco)
                .build();
    }
}
