package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "job_seekers")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cvs", "photoInfo"})
public class JobSeeker extends User {

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "tc_no")
    private String tcNo;

    @Column(name = "birth_year")
    private int birthYear;

    @OneToMany(mappedBy = "jobSeeker", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Cv> cvs;

    @OneToOne(mappedBy = "jobSeeker")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private PhotoInfo photoInfo;
}
