package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "cvs")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class Cv {
    @Column(name = "cv_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cvId;

    @Column(name = "photo")
    private String photo;

    @Column(name = "cover_letter")
    private String coverLetter;

    @JoinColumn(name = "user_id", nullable=false)
    @ManyToOne(fetch = FetchType.EAGER)
    private JobSeeker jobSeeker;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cv", cascade = CascadeType.ALL)
    private Set<PhotoInfo> photoInfo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cv", cascade = CascadeType.ALL)
    private Set<LanguagesForCv> languagesForCvs;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cv", cascade = CascadeType.ALL)
    private Set<EducationInformationForCv> educationInformationForCvs;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cv", cascade = CascadeType.ALL)
    private Set<WorkExperienceForCv> workExperienceForCvs;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cv", cascade = CascadeType.ALL)
    private Set<ProgrammingSkillForCv> programmingSkillForCvs;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cv", cascade = CascadeType.ALL)
    private Set<SocialMediaForCv> socialMediaForCvs;
}
