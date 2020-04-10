package reuse.dto.favorite;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Favorite;
import reuse.domain.Item;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class FavoriteListResponseView {
    private List<Favorite> favorites;

    @Builder
    public FavoriteListResponseView(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public static FavoriteListResponseView toDtoEntity(List<Favorite> favorites) {
        return FavoriteListResponseView.builder()
                .favorites(favorites)
                .build();
    }
}
