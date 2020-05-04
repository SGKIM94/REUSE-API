package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.Favorite;
import reuse.domain.User;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findEdgeByUser(User user);
}
