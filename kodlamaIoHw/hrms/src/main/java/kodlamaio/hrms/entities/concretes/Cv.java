package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "cvs")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="job_seeker_id",referencedColumnName = "job_seeker_id")

public class Cv {
    @Column(name = "cv_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cvId;

    @NotBlank
    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "job_seeker_id")
    private int jobSeekerId;

    @JsonIgnore
    @JoinColumn(name = "job_seeker_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = JobSeeker.class, fetch = FetchType.EAGER)
    private JobSeeker jobSeeker;

    @OneToMany(targetEntity=LanguagesForCv.class, mappedBy="cv", fetch = FetchType.LAZY)
    private List<LanguagesForCv> languagesForCvs;

    @OneToMany(targetEntity=EducationInformationForCv.class, mappedBy="cv", fetch = FetchType.LAZY)
    private List<EducationInformationForCv> educationInformationForCvs;

    @OneToMany(targetEntity=WorkExperienceForCv.class, mappedBy="cv", fetch = FetchType.LAZY)
    private List<WorkExperienceForCv> workExperienceForCvs;

    @OneToMany(targetEntity=ProgrammingSkillForCv.class, mappedBy="cv", fetch = FetchType.LAZY)
    private List<ProgrammingSkillForCv> programmingSkillForCvs;

    @OneToMany(targetEntity=SocialMediaForCv.class, mappedBy="cv", fetch = FetchType.LAZY)
    private List<SocialMediaForCv> socialMediaForCvs;

}
