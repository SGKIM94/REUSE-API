package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Item item;

    @Builder
    public Favorite(Long id, User user, Item item) {
        this.id = id;
        this.user = user;
        this.item = item;
    }

    public Favorite(User user, Item item) {
        this.id = 0L;
        this.user = user;
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }
}
