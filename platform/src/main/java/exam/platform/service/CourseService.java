package exam.platform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import exam.platform.dto.request.CourseRequest;
import exam.platform.entity.Category;
import exam.platform.entity.Course;
import exam.platform.entity.Enrollment;
import exam.platform.entity.User;
import exam.platform.exception.EntityNotFoundException;
import exam.platform.exception.InvalidRoleException;
import exam.platform.repository.CategoryRepository;
import exam.platform.repository.CourseRepository;
import exam.platform.repository.EnrollmentRepository;
import exam.platform.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final EnrollmentRepository enrollmentRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + id + " not found"));
    }

    public List<Course> getCoursesByUserId(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);

        return enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList());
    }

    public List<User> getStudentsByCourseId(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

        return enrollments.stream()
                .map(Enrollment::getUser)
                .collect(Collectors.toList());
    }

    public Course createCourse(Course course) {
        Long teacherId = course.getTeacher().getId();
        Long categoryId = course.getCategory().getId();

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + teacherId + " not found"));

        if (!teacher.getRole().equals(User.Role.TEACHER)) {
            throw new InvalidRoleException("Only users with the TEACHER role can lead courses.");
        }

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));

        course.setTeacher(teacher);
        course.setCategory(category);

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseRequest courseRequest) {
        Course existingCourse = getCourseById(id);

        // Обновляем только те поля, которые были переданы в запросе
        if (courseRequest.getTitle() != null) {
            existingCourse.setTitle(courseRequest.getTitle());
        }

        if (courseRequest.getDescription() != null) {
            existingCourse.setDescription(courseRequest.getDescription());
        }


        if (courseRequest.getTeacherId() != null) {
            User newTeacher = userRepository.findById(courseRequest.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher with id " + courseRequest.getTeacherId() + " not found"));

            if (!newTeacher.getRole().equals(User.Role.TEACHER)) {
                throw new InvalidRoleException("Only users with the TEACHER role can lead courses.");
            }

            existingCourse.setTeacher(newTeacher);
        }

        if (courseRequest.getCategoryId() != null) {
            Category newCategory = categoryRepository.findById(courseRequest.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category with id " + courseRequest.getCategoryId() + " not found"));
            existingCourse.setCategory(newCategory);
        }

        if (courseRequest.getStartDate() != null) {
            existingCourse.setStartDate(courseRequest.getStartDate());
        }

        if (courseRequest.getDuration() != null) {
            existingCourse.setDuration(courseRequest.getDuration());
        }

        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }
}
