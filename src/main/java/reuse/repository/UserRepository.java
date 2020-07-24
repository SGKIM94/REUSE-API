package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findBySocialTokenId(String socialTokenId);
}
