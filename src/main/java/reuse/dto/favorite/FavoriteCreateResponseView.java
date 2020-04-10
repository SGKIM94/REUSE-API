package reuse.dto.favorite;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Favorite;
import reuse.domain.Item;
import reuse.domain.User;

@Getter
@Builder
@NoArgsConstructor
public class FavoriteCreateResponseView {
    private Long id;
    private User user;
    private Item item;

    @Builder
    public FavoriteCreateResponseView(Long id, User user, Item item) {
        this.id = id;
        this.user = user;
        this.item = item;
    }

    public static FavoriteCreateResponseView toDtoEntity(Favorite favorite) {
        return FavoriteCreateResponseView.builder()
                .id(favorite.getId())
                .user(favorite.getUser())
                .item(favorite.getItem())
                .build();
    }
}
