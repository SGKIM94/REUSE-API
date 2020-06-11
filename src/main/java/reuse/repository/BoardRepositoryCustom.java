package reuse.repository;

import reuse.domain.Category;
import reuse.dto.board.ListBoardWithProductResponseView;

public interface BoardRepositoryCustom {
    ListBoardWithProductResponseView findAllByCategory(Category requestCategory);
}
