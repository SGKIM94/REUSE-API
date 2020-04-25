package reuse.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Item extends AbstractEntity {
    public Item(Long id) {
        super(id);
    }
}
