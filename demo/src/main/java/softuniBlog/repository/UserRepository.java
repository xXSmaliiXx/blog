package softuniBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuniBlog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User save(User user);
    User findByEmail(String email);
}
