package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.FavoriteBoard;
import reuse.domain.User;

import java.util.List;

public interface FavoriteBoardRepository extends JpaRepository<FavoriteBoard, Long>, FavoriteBoardRepositoryCustom {
    List<FavoriteBoard> findEdgeByUser(User user);
}
