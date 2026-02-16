package exam.platform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import exam.platform.dto.request.CourseReviewRequest;
import exam.platform.entity.Course;
import exam.platform.entity.CourseReview;
import exam.platform.entity.User;
import exam.platform.exception.EntityNotFoundException;
import exam.platform.repository.CourseRepository;
import exam.platform.repository.CourseReviewRepository;
import exam.platform.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseReviewService {
    private final CourseReviewRepository courseReviewRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<CourseReview> getAllCourseReviews() {
        return courseReviewRepository.findAll();
    }

    public CourseReview getCourseReviewById(Long id) {
        return courseReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CourseReview with id " + id + " not found"));
    }

    public CourseReview createCourseReview(CourseReview courseReview) {
        Long courseId = courseReview.getCourse().getId();
        Long studentId = courseReview.getStudent().getId();

        return createCourseReviewWithCourseIdAndStudentId(courseReview, courseId, studentId);
    }
    public CourseReview createCourseReview(Long courseId, Long studentId, CourseReview courseReviewDetails) {
        return createCourseReviewWithCourseIdAndStudentId(courseReviewDetails, courseId, studentId);
    }
    private CourseReview createCourseReviewWithCourseIdAndStudentId(CourseReview courseReview, Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + studentId + " not found"));

        courseReview.setCourse(course);
        courseReview.setStudent(student);

        return courseReviewRepository.save(courseReview);
    }

    public CourseReview updateCourseReview(Long id, CourseReviewRequest courseReviewRequest) {
        CourseReview existingReview = courseReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CourseReview with id " + id + " not found"));

        if (courseReviewRequest.getRating() != null) {
            existingReview.setRating(courseReviewRequest.getRating());
        }

        if (courseReviewRequest.getComment() != null) {
            existingReview.setComment(courseReviewRequest.getComment());
        }

        if (courseReviewRequest.getStudentId() != null) {
            User student = userRepository.findById(courseReviewRequest.getStudentId())
                    .orElseThrow(() -> new EntityNotFoundException("User with id " + courseReviewRequest.getStudentId() + " not found"));
            existingReview.setStudent(student);
        }

        if (courseReviewRequest.getCourseId() != null) {
            Course course = courseRepository.findById(courseReviewRequest.getCourseId())
                    .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseReviewRequest.getCourseId() + " not found"));
            existingReview.setCourse(course);
        }

        return courseReviewRepository.save(existingReview);
    }

    public void deleteCourseReview(Long id) {
        CourseReview review = courseReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CourseReview with id " + id + " not found"));
        courseReviewRepository.delete(review);
    }
}
