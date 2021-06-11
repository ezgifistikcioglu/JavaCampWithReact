package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cvs")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "job_seeker_id", referencedColumnName = "job_seeker_id")
public class Cv {
    @Column(name = "cv_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cvId;

    @Column(name = "photo")
    private String photo;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "job_seeker_id")
    private int jobSeekerId;

    @JsonIgnore
    @JoinColumn(name = "job_seeker_id", insertable = false, updatable = false)
    @ManyToOne()
    private JobSeeker jobSeeker;

    @OneToMany(mappedBy = "cv")
    private List<LanguagesForCv> languagesForCvs;

    @OneToMany(mappedBy = "cv")
    private List<EducationInformationForCv> educationInformationForCvs;

    @OneToMany(mappedBy = "cv")
    private List<WorkExperienceForCv> workExperienceForCvs;

    @OneToMany(mappedBy = "cv")
    private List<ProgrammingSkillForCv> programmingSkillForCvs;

    @OneToMany(mappedBy = "cv")
    private List<SocialMediaForCv> socialMediaForCvs;
}
