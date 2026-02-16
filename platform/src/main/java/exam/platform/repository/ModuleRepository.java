package exam.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import exam.platform.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
