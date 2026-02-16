package exam.platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name="start_date")
    private LocalDate startDate;
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id", nullable = false)
    private User teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <Module> modules = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="tag_course",
            joinColumns = @JoinColumn(name="course_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CourseReview> courseReviews = new ArrayList<>();

}
