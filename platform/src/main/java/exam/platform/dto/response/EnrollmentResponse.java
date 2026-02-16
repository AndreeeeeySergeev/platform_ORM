package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.CourseInfo;
import exam.platform.dto.nested.UserInfo;

import java.time.LocalDateTime;

@Data
public class EnrollmentResponse {
    private Long id;
    private UserInfo user;
    private CourseInfo course;
    private LocalDateTime enrollDate;
    private String status;
}
