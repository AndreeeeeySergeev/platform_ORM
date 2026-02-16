package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.QuizInfo;
import exam.platform.dto.nested.UserInfo;

import java.time.LocalDateTime;

@Data
public class QuizSubmissionResponse {
    private Long id;
    private Integer score;
    private LocalDateTime takenAt;
    private QuizInfo quiz;
    private UserInfo student;
}
