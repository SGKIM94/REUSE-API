package reuse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class Category extends AbstractEntity {
    private String teleco;

    private String manufacturer;

    private String model;

    private String deviceChange;

    @Builder
    public Category(String teleco, String manufacturer, String model, String deviceChange) {
        this.teleco = teleco;
        this.manufacturer = manufacturer;
        this.model = model;
        this.deviceChange = deviceChange;
    }

    public Category(Long id, String teleco, String manufacturer, String model, String deviceChange) {
        super(id);
        this.teleco = teleco;
        this.manufacturer = manufacturer;
        this.model = model;
        this.deviceChange = deviceChange;
    }
}
