package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "confirm_password")
    private String confirmPassword;

    @Column(name = "created_date",columnDefinition = "Default Date value - CURRENT_DATE")
    private LocalDate createdAt = LocalDate.now();

    @Column(name= "is_user_active", columnDefinition = "Default value - true")
    private boolean isActiveUser = true;

    @Column(name= "is_user_deleted", columnDefinition = "Default value - false")
    private boolean isDeletedUser = false;
}
