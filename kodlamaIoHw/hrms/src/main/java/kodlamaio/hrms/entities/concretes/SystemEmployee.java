package kodlamaio.hrms.entities.concretes;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hrms_employees")
@PrimaryKeyJoinColumn(name = "employees_id")
@AllArgsConstructor
@NoArgsConstructor
public class SystemEmployee extends User {

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;
}
