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

    @Builder
    public Category(String teleco, String manufacturer, String model) {
        this.teleco = teleco;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Category(Long id, String teleco, String manufacturer, String model) {
        super(id);
        this.teleco = teleco;
        this.manufacturer = manufacturer;
        this.model = model;
    }
}
