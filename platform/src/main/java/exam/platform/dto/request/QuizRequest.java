package exam.platform.dto.request;

import lombok.Data;

@Data
public class QuizRequest {
    private String title;
    private Integer timeLimit;
    private Long moduleId;
}
