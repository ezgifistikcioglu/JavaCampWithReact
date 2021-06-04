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
    @NotBlank
    @NotNull
    private String firstname;

    @Column(name = "lastname")
    @NotBlank
    @NotNull
    private String lastname;

    @Column(name = "tc_no")
    @NotBlank
    @NotNull
    private String tcNo;

    @Column(name = "birth_year")
    @NotBlank
    @NotNull
    private int birthYear;

}
