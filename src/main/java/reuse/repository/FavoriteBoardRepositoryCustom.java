package reuse.repository;

import reuse.dto.board.ListBoardWithProductResponseView;

public interface FavoriteBoardRepositoryCustom {
    ListBoardWithProductResponseView findAllByUserId(Long id);
}
