package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "email_verifications")
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private int emailId;

    @Column(name = "authentication")
    private String authentication;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "is_approved")
    private boolean isApproved;

    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne()
    private User user;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "activation_date")
    private LocalDateTime activationDate;
}
