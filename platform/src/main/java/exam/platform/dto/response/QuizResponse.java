package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.ModuleInfo;

import java.util.List;

@Data
public class QuizResponse {
    private Long id;
    private String title;
    private Integer timeLimit;
    private ModuleInfo module;
    private List<QuestionResponse> questions;
}
