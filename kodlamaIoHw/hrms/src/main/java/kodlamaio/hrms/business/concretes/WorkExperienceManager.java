package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.WorkExperienceService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.dataAccess.abstracts.WorkExperienceForCvRepository;
import kodlamaio.hrms.entities.concretes.WorkExperienceForCv;
import kodlamaio.hrms.entities.dtos.WorkExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkExperienceManager implements WorkExperienceService {

    private final WorkExperienceForCvRepository workExperienceForCvRepository;
    private final CVRepository cvRepository;


    @Autowired
    public WorkExperienceManager(WorkExperienceForCvRepository workExperienceForCvRepository, CVRepository cvRepository) {
        super();
        this.workExperienceForCvRepository = workExperienceForCvRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    public DataResult<List<WorkExperienceForCv>> getAll() {
        return new SuccessDataResult<>(this.workExperienceForCvRepository.findAll(), "Listed data");
    }

    @Override
    public Result add(WorkExperienceDto experienceDto) {
        if (findByExperienceId(experienceDto.getExperienceId()).getData() != null) {
            return new ErrorsResult(experienceDto.getExperienceId() + "Same experience cannot repeat");
        } else {
            WorkExperienceForCv workExperienceForCv = new WorkExperienceForCv();
            customField(experienceDto, workExperienceForCv);
            return new SuccessResult("Added new experience");
        }
    }

    private void customField(WorkExperienceDto experienceDto, WorkExperienceForCv workExperienceForCv) {
        workExperienceForCv.setBusinessName(experienceDto.getBusinessName());
        workExperienceForCv.setJobName(experienceDto.getJobName());
        workExperienceForCv.setStillWorking(experienceDto.isStillWorking());
        workExperienceForCv.setBusinessLeavingDate(experienceDto.getBusinessLeavingDate());
        workExperienceForCv.setBusinessStartDate(experienceDto.getBusinessStartDate());
        workExperienceForCv.setCreatedAt(experienceDto.getCreatedAt());
        workExperienceForCv.setCv(this.cvRepository.getOne(experienceDto.getCvId()));
        this.workExperienceForCvRepository.save(workExperienceForCv);
    }

    @Override
    public Result update(WorkExperienceDto experienceDto) {
        WorkExperienceForCv workExperienceForCv = this.workExperienceForCvRepository.getOne(experienceDto.getExperienceId());
        customField(experienceDto, workExperienceForCv);
        return new SuccessResult("Updated experience");
    }

    @Override
    public Result delete(int id) {
        Optional<WorkExperienceForCv> experienceForCv = this.workExperienceForCvRepository.findById(id);

        if (!experienceForCv.isPresent()) {
            return new ErrorDataResult<>("Experience not found");
        } else {
            this.workExperienceForCvRepository.deleteById(id);
            return new SuccessResult("Deleted experience");
        }
    }


    @Override
    public DataResult<List<WorkExperienceForCv>> findByExperienceId(int id) {
        List<WorkExperienceForCv> experience = workExperienceForCvRepository.findByExperienceId(id);

        if (experience.isEmpty()) {
            return new ErrorDataResult<>("This Work Experience Not Found");
        } else {
            return new SuccessDataResult<>(experience);
        }
    }

    @Override
    public DataResult<List<WorkExperienceForCv>> findByExperienceIdOrderByBusinessLeavingDate(int idWithoutDesc) {
        return new SuccessDataResult<>(workExperienceForCvRepository.findByExperienceIdOrderByBusinessLeavingDate(idWithoutDesc));
    }

    @Override
    public DataResult<List<WorkExperienceForCv>> findByExperienceIdOrderByBusinessLeavingDateDesc(int idWithDesc, Sort.Direction direction) {
        List<WorkExperienceForCv> jobSeekerCVEducations = direction.isAscending()
                ? workExperienceForCvRepository.findByExperienceIdOrderByBusinessLeavingDate(idWithDesc)
                : workExperienceForCvRepository.findByExperienceIdOrderByBusinessLeavingDateDesc(idWithDesc);

        return new SuccessDataResult<>(jobSeekerCVEducations);
    }
}
