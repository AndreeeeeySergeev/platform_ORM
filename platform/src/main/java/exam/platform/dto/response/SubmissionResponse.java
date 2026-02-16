package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.AssignmentInfo;
import exam.platform.dto.nested.UserInfo;

import java.time.LocalDateTime;

@Data
public class SubmissionResponse {
    private Long id;
    private String content;
    private LocalDateTime submittedAt;
    private Integer score;
    private String feedback;
    private AssignmentInfo assignment;
    private UserInfo student;
}
