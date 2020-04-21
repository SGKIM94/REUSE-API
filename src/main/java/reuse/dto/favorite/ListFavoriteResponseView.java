package reuse.dto.favorite;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Favorite;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
public class ListFavoriteResponseView {
    private List<Favorite> favorites;

    @Builder
    public ListFavoriteResponseView(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public static ListFavoriteResponseView toDtoEntity(List<Favorite> favorites) {
        return ListFavoriteResponseView.builder()
                .favorites(favorites)
                .build();
    }
}
