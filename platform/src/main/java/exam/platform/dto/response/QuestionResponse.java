package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.QuizInfo;

import java.util.List;

@Data
public class QuestionResponse {
    private Long id;
    private String text;
    private String type;
    private QuizInfo quiz;
    private List<AnswerOptionResponse> options;
}
