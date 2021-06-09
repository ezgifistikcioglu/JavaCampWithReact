package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "job_seekers")
@PrimaryKeyJoinColumn(name = "job_seeker_id")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cvs"})
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

    @Column(name = "photos", nullable = true, length = 64)
    private String photos;

    @JsonIgnore
    @OneToMany(mappedBy = "jobSeeker")
    private List<Cv> cvs;
}
