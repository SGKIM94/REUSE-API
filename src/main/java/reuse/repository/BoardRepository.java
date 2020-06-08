package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
