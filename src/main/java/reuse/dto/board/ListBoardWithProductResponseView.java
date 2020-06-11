package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListBoardWithProductResponseView {
    private List<FindWithProductResponseView> boards;

    @Builder
    public ListBoardWithProductResponseView(List<FindWithProductResponseView> boards) {
        this.boards = boards;
    }

    public static ListBoardWithProductResponseView toDto(List<FindWithProductResponseView> boards) {
        return new ListBoardWithProductResponseView(boards);
    }

    public int getSize() {
        return boards.size();
    }

    public FindWithProductResponseView getFirstIndex() {
        return boards.get(0);
    }

    public FindWithProductResponseView getSecondIndex() {
        return boards.get(1);
    }
}
