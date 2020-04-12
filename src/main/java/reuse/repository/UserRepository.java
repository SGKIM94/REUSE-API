package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.User;
import reuse.dto.user.FindBySocialTokenIdResponseView;

public interface UserRepository extends JpaRepository<User, Long> {
    FindBySocialTokenIdResponseView findBySocialTokenId(String socialTokenId);
}
