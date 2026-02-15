package exam.platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

    public enum QuestionType {
        SINGLE_CHOICE, MULTIPLE_CHOICE
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quiz_id", nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AnswerOption> options = new ArrayList<>();
}
