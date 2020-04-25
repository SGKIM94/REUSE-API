package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
@NoArgsConstructor
public class Favorite extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Item item;

    @Builder
    public Favorite(Long id, User user, Item item) {
        super(id);
        this.user = user;
        this.item = item;
    }

    public Favorite(User user, Item item) {
        super(1L);
        this.user = user;
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public Item getItem() {
        return item;
    }
}
