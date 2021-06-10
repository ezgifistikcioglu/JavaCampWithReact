package kodlamaio.hrms.entities.dtos;

import kodlamaio.hrms.entities.concretes.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvDetailForJobSeekerDto {
    private int id;
    private String photo;
    private String coverLetter;
    private JobSeeker jobSeeker;

    private List<LanguagesForCv> languagesForCvs;
    private List<EducationInformationForCv> educationInformationForCvs;
    private List<WorkExperienceForCv> workExperienceForCvs;
    private List<ProgrammingSkillForCv> programmingSkillForCvs;
    private List<SocialMediaForCv> socialMediaForCvs;

    public CvDetailForJobSeekerDto(int id, String photo, String coverLetter, JobSeeker jobSeeker) {
        super();
        this.id = id;
        this.photo = photo;
        this.coverLetter = coverLetter;
        this.jobSeeker = jobSeeker;
    }
}
