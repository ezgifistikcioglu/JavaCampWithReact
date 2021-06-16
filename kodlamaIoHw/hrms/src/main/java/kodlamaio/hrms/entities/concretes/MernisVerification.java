package kodlamaio.hrms.entities.concretes;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "mernis_verifications")
public class MernisVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JoinColumn(name = "user_id")
    @OneToOne()
    private User user;

    @Column(name = "is_approved", columnDefinition = "boolean default false")
    private boolean isApproved = false;

    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

}
