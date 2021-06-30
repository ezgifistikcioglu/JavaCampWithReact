package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "job_seekers")
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<Cv> cvs;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "photo_id")
    private PhotoInfo photoInfo;
}
