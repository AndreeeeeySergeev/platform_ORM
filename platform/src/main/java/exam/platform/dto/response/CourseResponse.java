package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.CategoryInfo;
import exam.platform.dto.nested.UserInfo;

import java.time.LocalDate;

@Data
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private UserInfo teacher;
    private CategoryInfo category;
    private LocalDate startDate;
    private Integer duration;
}
