package reuse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
public class Product extends AbstractEntity {
    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 1, max = 200)
    private String explanation;

    private String price;

    private String tax;

    private Boolean isUsed;

    private Boolean isSold;

    private String categoryId;

    @Builder
    public Product(Long id, String name, String explanation, String price, String tax, Boolean isUsed,
                   Boolean isSold, String categoryId) {
        super(id);
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.tax = tax;
        this.isUsed = isUsed;
        this.isSold = isSold;
        this.categoryId = categoryId;
    }
}
