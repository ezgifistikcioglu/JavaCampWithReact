package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "job_seekers")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cvs", "photoInfo","languagesForCvs","educationInformationForCvs","workExperienceForCvs","programmingSkillForCvs","socialMediaForCvs"})
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
    private List<Cv> cvs;

    @OneToOne(mappedBy = "jobSeeker")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private PhotoInfo photoInfo;

    @OneToMany(mappedBy = "jobSeeker")
    private List<LanguagesForCv> languagesForCvs;

    @OneToMany(mappedBy = "jobSeeker")
    private List<EducationInformationForCv> educationInformationForCvs;

    @OneToMany(mappedBy = "jobSeeker")
    private List<WorkExperienceForCv> workExperienceForCvs;

    @OneToMany(mappedBy = "jobSeeker")
    private List<ProgrammingSkillForCv> programmingSkillForCvs;

    @OneToMany(mappedBy = "jobSeeker")
    private List<SocialMediaForCv> socialMediaForCvs;
}
