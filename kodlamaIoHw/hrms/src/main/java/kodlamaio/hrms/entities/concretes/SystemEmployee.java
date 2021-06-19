package kodlamaio.hrms.entities.concretes;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "hrms_employees")
@AllArgsConstructor
@NoArgsConstructor
public class SystemEmployee {
    @Column(name = "id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    @OneToOne()
    private User user;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "is_approved", columnDefinition = "boolean default false")
    private boolean isApproved = false;

    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private final LocalDateTime createdAt = LocalDateTime.now();
}
