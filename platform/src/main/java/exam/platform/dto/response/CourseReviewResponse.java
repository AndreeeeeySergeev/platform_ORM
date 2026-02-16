package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.CourseInfo;
import exam.platform.dto.nested.UserInfo;

import java.time.LocalDateTime;

@Data
public class CourseReviewResponse {
    private Long id;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
    private CourseInfo course;
    private UserInfo student;
}
