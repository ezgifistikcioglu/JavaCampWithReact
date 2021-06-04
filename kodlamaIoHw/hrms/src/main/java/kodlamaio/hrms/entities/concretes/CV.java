package kodlamaio.hrms.entities.concretes;

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

public class CV {
    @Column(name = "cv_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cvId;

    @NotBlank
    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "job_seeker_id")
    private int jobSeekerId;

    @NotNull
    @JoinColumn(name = "job_seeker_id", insertable = false, updatable = false)
    @ManyToOne()
    private JobSeeker jobSeeker;

    @OneToMany()
    private List<LanguagesForCv> languagesForCvs;

    @OneToMany()
    private List<EducationInformationForCv> educationInformationForCvs;

    @OneToMany()
    private List<WorkExperienceForCv> workExperienceForCvs;

    @OneToMany()
    private List<ProgrammingSkillForCv> programmingSkillForCvs;

    @OneToMany()
    private List<SocialMediaForCv> socialMediaForCvs;
}
