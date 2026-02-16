package exam.platform.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    private int telephone;
    @Column(nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        STUDENT, TEACHER, ADMIN
    }

    public User() {}

    public User (String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> coursesTaught = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Submission> submissions = new ArrayList<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <QuizSubmission> quizSubmissions = new ArrayList<>();

    @OneToMany(mappedBy = "student", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CourseReview> reviews = new ArrayList<>();
}
