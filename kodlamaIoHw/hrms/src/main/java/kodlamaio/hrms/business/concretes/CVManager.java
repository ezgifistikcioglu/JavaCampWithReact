package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.*;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.dataAccess.abstracts.UserRepository;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.dtos.CvDetailForJobSeekerDto;
import kodlamaio.hrms.entities.dtos.CvDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CVManager implements CVService {

    private final CVRepository cvRepository;
    private final EducationInformationService informationService;
    private final LanguageService languageService;
    private final WorkExperienceService experienceService;
    private final ProgrammingSkillService skillService;
    private final SocialMediaService socialMediaService;
    private final JobSeekerService jobSeekerService;
    private final PhotoService photoService;
    private final UserRepository userRepository;


    @Autowired
    public CVManager(CVRepository cvRepository, EducationInformationService informationService, LanguageService languageService, WorkExperienceService experienceService, ProgrammingSkillService skillService, SocialMediaService socialMediaService, JobSeekerService jobSeekerService, PhotoService photoService, UserRepository userRepository) {
        super();
        this.cvRepository = cvRepository;
        this.informationService = informationService;
        this.languageService = languageService;
        this.experienceService = experienceService;
        this.skillService = skillService;
        this.socialMediaService = socialMediaService;
        this.jobSeekerService = jobSeekerService;
        this.photoService = photoService;
        this.userRepository = userRepository;
    }

    @Override
    public DataResult<List<Cv>> getAll() {
        List<Cv> cvs = this.cvRepository.findAll();
        return new SuccessDataResult<>(cvs, "Cv listed successfully");
    }

    @Override
    public DataResult<Cv> getByCvId(int cvId) {
        Cv cv = cvRepository.findByCvId(cvId);

        if (cv == null)
            return new ErrorDataResult<>("This CV Not Found");

        return new SuccessDataResult<>(cv);
    }

    @Override
    public DataResult<Cv> findByJobSeekerUserId(int id) {
        Cv cv = cvRepository.findByJobSeekerUserId(id);
        if (cv == null){
            return new ErrorDataResult<>("This CV Not Found");
        }else{
            return new SuccessDataResult<>(cv,"Data Listed");
        }
    }

    @Override
    public DataResult<CvDetailForJobSeekerDto> getCvDetailForJobSeekerById(int cvId) {
        CvDetailForJobSeekerDto jobSeekerDto = this.cvRepository.getCvDetailForJobSeekerById(cvId);

        if (jobSeekerDto == null) {
            return new ErrorDataResult<>("This cv not found");
        } else {
            jobSeekerDto.setEducationInformationForCvs(this.informationService.findByEducationId(jobSeekerDto.getJobSeeker().getUserId()).getData());
            jobSeekerDto.setPhotoInfo(this.photoService.findAllById(jobSeekerDto.getId()).getData());
            jobSeekerDto.setWorkExperienceForCvs(this.experienceService.findByExperienceId(jobSeekerDto.getId()).getData());
            jobSeekerDto.setLanguagesForCvs(this.languageService.findAllByLanguageId(jobSeekerDto.getId()).getData());
            jobSeekerDto.setSocialMediaForCvs(this.socialMediaService.findAllByCvId(jobSeekerDto.getId()).getData());
            jobSeekerDto.setProgrammingSkillForCvs(this.skillService.findAllByCvId(jobSeekerDto.getId()).getData());
            jobSeekerDto.setJobSeeker(this.jobSeekerService.getById(jobSeekerDto.getId()).getData());
            return new SuccessDataResult<>(jobSeekerDto, "CV information has been successfully retrieved.");
        }
    }

    @Override
    public Result add(CvDto cvDto) {
        if (getByCvId(cvDto.getCvId()).getData() != null) {
            return new ErrorsResult(cvDto.getCvId() + "Same cv cannot repeat");
        } else {
            Cv cv = new Cv();
            commonField(cvDto, cv);
            return new SuccessResult("Added new cv");
        }
    }

    @Override
    public Result update(CvDto cvDto) {
        Cv cv = this.cvRepository.findByCvId(cvDto.getCvId());
        if (!this.userRepository.findById(cvDto.getUserId()).isPresent()) {
            return new ErrorsResult("There is not available system employee with this id! : " + cvDto.getUserId() + "!");
        } else {
            // User user = this.userRepository.findById(cvDto.getUserId()).get();
            if (cv == null) {
                return new ErrorsResult("This cv ( id " + Objects.requireNonNull(cv).getCvId() + " ) doesnt available!");
            } else {
                commonField(cvDto, cv);
                return new SuccessResult("Updated cv");
            }
        }
    }

    private void commonField(CvDto cvDto, Cv cv) {
        cv.setCoverLetter(cvDto.getCoverLetter());
        cv.setJobSeeker(this.jobSeekerService.getById(cvDto.getUserId()).getData());
        cv.setPhotoInfo(this.photoService.findAllById(cvDto.getPhotoId()).getData());
        cv.setLanguagesForCvs(this.languageService.findAllByLanguageId(cvDto.getLanguageId()).getData());
        cv.setEducationInformationForCvs(this.informationService.findByEducationId(cvDto.getEducationId()).getData());
        cv.setWorkExperienceForCvs(this.experienceService.findByExperienceId(cvDto.getExperienceId()).getData());
        cv.setProgrammingSkillForCvs(this.skillService.findAllByCvId(cvDto.getSkillId()).getData());
        cv.setSocialMediaForCvs(this.socialMediaService.findAllByCvId(cvDto.getSocialMediaId()).getData());
        this.cvRepository.save(cv);
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
