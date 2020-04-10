package reuse.domain;

import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@NoArgsConstructor
public class Item {
    @OneToOne
    private Long id;

    public Item(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
