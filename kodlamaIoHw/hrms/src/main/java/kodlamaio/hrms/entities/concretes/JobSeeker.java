package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "job_seekers")
@PrimaryKeyJoinColumn(name = "job_seeker_id")
@AllArgsConstructor
@NoArgsConstructor
public class JobSeeker extends User {

    @Column(name = "firstname")
    @NotNull
    private String firstname;

    @Column(name = "lastname")
    @NotNull
    private String lastname;

    @Column(name = "tc_no")
    @NotNull
    private String tcNo;

    @Column(name = "birth_year")
    @NotNull
    private int birthYear;

}
