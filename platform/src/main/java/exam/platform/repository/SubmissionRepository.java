package exam.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import exam.platform.entity.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}

