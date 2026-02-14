package exam.platform.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private int telephone;
    @Column(nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        STUDENT, TEACHER, ADMIN
    }

    public User() {}

    public User (String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


}
