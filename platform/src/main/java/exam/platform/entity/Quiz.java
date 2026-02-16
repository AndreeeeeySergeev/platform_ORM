package exam.platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="quizzes")
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=100, nullable = false)
    private String title;

    @Column(name="time_limit")
    private Integer timeLimit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="module_id", nullable = false)
    private Module module;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizSubmission> quizSubmissions = new ArrayList<>();

}
