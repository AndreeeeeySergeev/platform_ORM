package exam.platform.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="lessons")
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

}
