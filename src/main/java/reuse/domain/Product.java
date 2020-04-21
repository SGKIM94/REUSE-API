package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 1, max = 200)
    private String explanation;

    private String price;

    private String tax;

    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    private Boolean isUsed;
    private Boolean isSold;

    private String categoryId;

    @Builder
    public Product(String name, String explanation, String price, String tax, LocalDateTime registerDate,
                   LocalDateTime updateDate, Boolean isUsed, Boolean isSold, String categoryId) {
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.tax = tax;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.isUsed = isUsed;
        this.isSold = isSold;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }
}
