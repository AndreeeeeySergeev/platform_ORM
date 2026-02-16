package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.CourseInfo;

@Data
public class ModuleResponse {
    private Long id;
    private String title;
    private Integer orderIndex;
    private CourseInfo course;
}
