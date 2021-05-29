package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "mernis_verification")
@AllArgsConstructor
@NoArgsConstructor
public class MernisVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name = "is_approved")
    private boolean isApproved;
}
