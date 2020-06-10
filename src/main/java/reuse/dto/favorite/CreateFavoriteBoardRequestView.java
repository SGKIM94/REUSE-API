package reuse.dto.favorite;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFavoriteBoardRequestView {
    private Long boardId;

    @Builder
    public CreateFavoriteBoardRequestView(Long boardId) {
        this.boardId = boardId;
    }
}
