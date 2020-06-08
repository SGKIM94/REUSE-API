package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListBoardByCategoryResponseView {
    private List<FindByCategoryResponseView> boards;

    @Builder
    public ListBoardByCategoryResponseView(List<FindByCategoryResponseView> boards) {
        this.boards = boards;
    }

    public static ListBoardByCategoryResponseView toDto(List<FindByCategoryResponseView> boards) {
        return new ListBoardByCategoryResponseView(boards);
    }

    public int getSize() {
        return boards.size();
    }

    public FindByCategoryResponseView getFirstIndex() {
        return boards.get(0);
    }

    public FindByCategoryResponseView getSecondIndex() {
        return boards.get(1);
    }
}
