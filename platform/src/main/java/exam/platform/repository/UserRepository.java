package exam.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import exam.platform.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
