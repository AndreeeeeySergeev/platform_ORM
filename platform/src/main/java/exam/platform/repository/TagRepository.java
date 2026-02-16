package exam.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import exam.platform.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
