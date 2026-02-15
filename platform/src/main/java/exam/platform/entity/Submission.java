package exam.platform.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="submissions")
@Data
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="submitted_at")
    private LocalDateTime submittedAt;

    private String content;

    private Integer Score;
    @Column(length=300)
    private String feedback;

    @PrePersist
    protected void countDate() {submittedAt = LocalDateTime.now();}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id", nullable = false)
    private User student;

}
