package service;

import exam.platform.PlatformApplication;
import exam.platform.entity.Course;
import exam.platform.entity.User;
import exam.platform.entity.Category;
import exam.platform.entity.User.Role;
import exam.platform.repository.CourseRepository;
import exam.platform.repository.UserRepository;
import exam.platform.repository.CategoryRepository;
import exam.platform.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PlatformApplication.class)
@ActiveProfiles("test")
class CourseServiceIntegrationTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    private Category category;
    private User teacher;

    @BeforeEach
    void setUp() {
        // Создаём тестовую категорию и преподавателя
        category = new Category();
        category.setName("Java Development");
        category = categoryRepository.save(category);

        teacher = new User();
        teacher.setFirstName("Professor Adam");
        teacher.setFirstName("Smith");
        teacher.setRole(Role.TEACHER);
        teacher = userRepository.save(teacher);
    }

    @Test
    void shouldCreateCourseWithTeacher() {
        // Given: готовим данные для курса
        Course course = new Course();
        course.setTitle("Advanced Java");
        course.setDescription("Deep dive into Java 17 features");
        course.setTeacher(teacher);
        course.setCategory(category);

        // When: создаём курс через сервис
        Course createdCourse = courseService.createCourse(course);

        // Then: проверяем корректность создания
        assertThat(createdCourse).isNotNull();
        assertThat(createdCourse.getId()).isNotNull();
        assertThat(createdCourse.getTeacher().getId()).isEqualTo(teacher.getId());
        assertThat(createdCourse.getCategory().getId()).isEqualTo(category.getId());
    }

    @Test
    void shouldFindCoursesByCategory() {
        // Given: создаём несколько курсов в одной категории
        Course course1 = createCourse("Course 1", "Desc 1");
        Course course2 = createCourse("Course 2", "Desc 2");

        // When: получаем курсы по категории
        List<Course> courses = courseService.getCoursesByUserId(category.getId());

        // Then: проверяем результат
        assertThat(courses).hasSize(2);
        assertThat(courses)
                .extracting("Java Development")
                .containsExactlyInAnyOrder("Course 1", "Course 2");
    }

    private Course createCourse(String title, String description) {
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setTeacher(teacher);
        course.setCategory(category);
        return courseRepository.save(course);
    }
}
