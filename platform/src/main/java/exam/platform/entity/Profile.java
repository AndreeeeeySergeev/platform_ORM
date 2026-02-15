package exam.platform.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="profiles")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=200)
    private String bio;
    @Column(name="avatar")
    private String avatarUrl;
    @Column(nullable=true)
    private String address;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}
