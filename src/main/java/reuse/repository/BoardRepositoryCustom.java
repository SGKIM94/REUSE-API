package reuse.repository;

import reuse.domain.Category;
import reuse.dto.board.ListBoardByCategoryResponseView;

public interface BoardRepositoryCustom {
    ListBoardByCategoryResponseView findAllByCategory(Category requestCategory);
}
