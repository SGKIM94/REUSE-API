package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Board;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ListBoardResponseView {
    private List<FindBoardResponseView> boards;

    @Builder
    public ListBoardResponseView(List<FindBoardResponseView> boards) {
        this.boards = boards;
    }

    public static ListBoardResponseView toDto(List<Board> boards) {
        return new ListBoardResponseView(boards.stream()
                .map(FindBoardResponseView::toDto)
                .collect(Collectors.toList()));
    }

    public int getSize() {
        return this.boards.size();
    }
}
