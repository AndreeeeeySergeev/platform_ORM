package exam.platform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import exam.platform.entity.Course;
import exam.platform.entity.Enrollment;
import exam.platform.entity.User;
import exam.platform.exception.DuplicateEntityException;
import exam.platform.exception.EntityNotFoundException;
import exam.platform.repository.CourseRepository;
import exam.platform.repository.EnrollmentRepository;
import exam.platform.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment with id " + id + " not found"));
    }

    @Transactional
    public Enrollment enrollUserToCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        // Проверка, что пользователь ещё не записан на этот курс
        boolean alreadyEnrolled = enrollmentRepository.existsByUserIdAndCourseId(userId, courseId);
        if (alreadyEnrolled) {
            throw new DuplicateEntityException("User is already enrolled in this course.");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDateTime.now());
        enrollment.setStatus(Enrollment.EnrollStatus.ACTIVE);

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void unenrollUserFromCourse(Long userId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found for user " + userId + " and course " + courseId));

        enrollmentRepository.delete(enrollment);
    }
}
