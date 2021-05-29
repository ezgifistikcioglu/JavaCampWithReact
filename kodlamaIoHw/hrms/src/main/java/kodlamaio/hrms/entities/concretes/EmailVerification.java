package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "email_verification")
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
    private String email;

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(name="user_id")
    private int userId;

}
