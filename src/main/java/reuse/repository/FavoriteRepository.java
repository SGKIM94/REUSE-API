package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.FavoriteBoard;
import reuse.domain.User;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteBoard, Long> {
    List<FavoriteBoard> findEdgeByUser(User user);
}
