package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@Entity
@Table(name = "email_verifications")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id", unique = true, nullable = false)
    private int emailId;

    @Column(name = "authentication")
    private String authentication;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "is_approved")
    private boolean isApproved;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private User user;

}
