package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "employer_id")
@AllArgsConstructor
@NoArgsConstructor
public class Employer extends User {

    @Column(name = "company_name")
    private String firstname;

    @Column(name = "telephone_no")
    private String lastname;

    @Column(name = "web_address")
    private String tc_no;

}
