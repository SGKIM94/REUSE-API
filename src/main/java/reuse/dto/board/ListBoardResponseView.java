package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListBoardResponseView {
    private List<FindBoardResponseView> boards;

    @Builder
    public ListBoardResponseView(List<FindBoardResponseView> boards) {
        this.boards = boards;
    }

    public int getSize() {
        return this.boards.size();
    }
}
