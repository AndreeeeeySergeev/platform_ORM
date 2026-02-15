package exam.platform.entity;

import jakarta.persistence.*;

public class Quiz {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="module_id", nullable = false)
    private Module module;
}
