package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindAllByCategoryResponseViews {
    private List<FindAllByCategoryResponseView> boards;

    @Builder
    public FindAllByCategoryResponseViews(List<FindAllByCategoryResponseView> boards) {
        this.boards = boards;
    }

    public static FindAllByCategoryResponseViews toDto(List<FindAllByCategoryResponseView> boards) {
        return new FindAllByCategoryResponseViews(boards);
    }

    public FindAllByCategoryResponseView getFirstIndex() {
        return boards.get(0);
    }

    public FindAllByCategoryResponseView getSecondIndex() {
        return boards.get(1);
    }
}
