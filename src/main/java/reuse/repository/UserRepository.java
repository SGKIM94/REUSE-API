package reuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reuse.domain.User;
import reuse.dto.user.FindByEmailResponseView;

public interface UserRepository extends JpaRepository<User, Long> {
    FindByEmailResponseView findByEmail(String email);
}
