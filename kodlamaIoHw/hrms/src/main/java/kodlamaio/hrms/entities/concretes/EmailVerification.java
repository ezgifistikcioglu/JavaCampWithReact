package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "email_verifications")
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "authentication")
    private String authentication;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "is_approved")
    private boolean isApproved;

    @NotNull
    @JoinColumn(name = "user_id")
    @ManyToOne()
    private User user;

    @NotNull
    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private final LocalDateTime createdAt = LocalDateTime.now();


    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @NotNull
    @Column(name = "activation_date")
    private LocalDateTime activationDate;
}
