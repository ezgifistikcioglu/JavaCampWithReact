package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
public class Employer extends User {

    @Column(name = "employer_id")
    private String employer_id;

    @Column(name = "company_name")
    private String firstname;

    @Column(name = "telephone_no")
    private String lastname;

    @Column(name = "web_address")
    private String tc_no;

    @Column(name = "birth_year")
    private Date birth_year;
}
