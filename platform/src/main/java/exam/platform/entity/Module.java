package exam.platform.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="modules")
@Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
