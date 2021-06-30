package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "cvs")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class Cv {
    @Id
    @Column(name = "cv_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cvId;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "creation_date", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdDate = LocalDateTime.now();

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = JobSeeker.class)
    private JobSeeker jobSeeker;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PhotoInfo> photoInfo;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LanguagesForCv> languagesForCvs;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<EducationInformationForCv> educationInformationForCvs;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<WorkExperienceForCv> workExperienceForCvs;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProgrammingSkillForCv> programmingSkillForCvs;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<SocialMediaForCv> socialMediaForCvs;
}
