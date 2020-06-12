package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.FavoriteBoard;
import reuse.dto.board.ListBoardWithProductResponseView;

public interface FavoriteBoardRepository extends JpaRepository<FavoriteBoard, Long>, FavoriteBoardRepositoryCustom {
    @Override
    ListBoardWithProductResponseView findAllByUserId(Long id);
}
