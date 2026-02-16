package exam.platform.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Integer telephone;
    private String role;
}
