package exam.platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="assignments")
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name="due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "max_score")
    private Integer maxScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lesson_id", nullable = false)
    private Lesson lesson;

    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Submission> submissions = new ArrayList<>();
}
