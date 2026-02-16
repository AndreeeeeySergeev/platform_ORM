package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.ModuleInfo;

@Data
public class LessonResponse {
    private Long id;
    private String title;
    private String content;
    private ModuleInfo module;
}
