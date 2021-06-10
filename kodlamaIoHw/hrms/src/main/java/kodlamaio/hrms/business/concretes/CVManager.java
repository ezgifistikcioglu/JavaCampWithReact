package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.*;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.dtos.CvDetailForJobSeekerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CVManager implements CVService {

    private CVRepository cvRepository;
    private EducationInformationService informationService;
    private LanguageService languageService;
    private WorkExperienceService experienceService;
    private ProgrammingSkillService skillService;
    private SocialMediaService socialMediaService;
    private JobSeekerService jobSeekerService;


    @Autowired
    public CVManager(CVRepository cvRepository, EducationInformationService informationService, LanguageService languageService, WorkExperienceService experienceService, ProgrammingSkillService skillService, SocialMediaService socialMediaService, JobSeekerService jobSeekerService) {
        super();
        this.cvRepository = cvRepository;
        this.informationService = informationService;
        this.languageService = languageService;
        this.experienceService = experienceService;
        this.skillService = skillService;
        this.socialMediaService = socialMediaService;
        this.jobSeekerService = jobSeekerService;
    }

    @Override
    public DataResult<List<Cv>> getAll() {
        return new SuccessDataResult<List<Cv>>(this.cvRepository.findAll(), "Listed data");
    }

    @Override
    public DataResult<Cv> getByCvId(int cvId) {
        Optional<Cv> cv = cvRepository.getByCvId(cvId);

        if (!cv.isPresent())
            return new ErrorDataResult<Cv>("This CV Not Found");

        return new SuccessDataResult<Cv>(cv.get());
    }

    @Override
    public DataResult<CvDetailForJobSeekerDto> getCvDetailForJobSeekerById(int cvId) {
        CvDetailForJobSeekerDto jobSeekerDto = this.cvRepository.getCvDetailForJobSeekerById(cvId);

        if (jobSeekerDto.equals(null)){
            return new ErrorDataResult<>("This cv not found");
        }else {
            jobSeekerDto.setEducationInformationForCvs(this.informationService.findByEducationId(jobSeekerDto.getJobSeeker().getId()).getData());
            jobSeekerDto.setWorkExperienceForCvs(this.experienceService.findByExperienceId(jobSeekerDto.getId()).getData());
            jobSeekerDto.setLanguagesForCvs(this.languageService.findAllByLanguageId(jobSeekerDto.getId()).getData());
            jobSeekerDto.setSocialMediaForCvs(this.socialMediaService.findAllByCvId(jobSeekerDto.getId()).getData());
            jobSeekerDto.setProgrammingSkillForCvs(this.skillService.findAllByCvId(jobSeekerDto.getId()).getData());
            return new SuccessDataResult<>(jobSeekerDto,"CV information has been successfully retrieved.");
        }
    }

    @Override
    public Result add(Cv cv) {
        if (getByCvId(cv.getCvId()).getData() != null) {
            return new ErrorsResult(cv.getCvId() + "Same cv cannot repeat");
        } else {
            this.cvRepository.save(cv);
            return new SuccessResult("Added new cv");
        }
    }

    @Override
    public Result update(Cv cv) {
        this.cvRepository.save(cv);
        return new SuccessResult("Updated cv");
    }

    @Override
    public Result delete(int id) {
        Optional<Cv> cv = this.cvRepository.findById(id);

        if (!cv.isPresent()) {
            return new ErrorDataResult<>("Cv not found");
        } else {
            this.cvRepository.deleteById(id);
            return new SuccessResult("Deleted cv with id :" + id);
        }
    }
}
