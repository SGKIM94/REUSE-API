package reuse.dto.favorite;

import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Favorite;
import reuse.domain.Item;
import reuse.domain.User;

@Getter
@NoArgsConstructor
public class CreateFavoriteRequestView {
    private Item item;
    private String type;

    public CreateFavoriteRequestView(Item item, String type) {
        this.item = item;
        this.type = type;
    }

    public Favorite toEntity(User user) {
        return Favorite.builder()
                .user(user)
                .item(item)
                .build();
    }
}
