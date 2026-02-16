package exam.platform.dto.response;

import lombok.Data;
import exam.platform.dto.nested.UserInfo;

@Data
public class ProfileResponse {
    private Long id;
    private String bio;
    private String avatarUrl;
    private String address;
    private UserInfo user;
}
