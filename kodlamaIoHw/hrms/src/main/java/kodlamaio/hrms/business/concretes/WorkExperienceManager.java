package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.WorkExperienceService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.WorkExperienceForCvRepository;
import kodlamaio.hrms.entities.concretes.WorkExperienceForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceManager implements WorkExperienceService {

    private WorkExperienceForCvRepository workExperienceForCvRepository;

    @Autowired
    public WorkExperienceManager(WorkExperienceForCvRepository workExperienceForCvRepository) {
        super();
        this.workExperienceForCvRepository = workExperienceForCvRepository;
    }

    @Override
    public DataResult<List<WorkExperienceForCv>> getAll() {
        return new SuccessDataResult<List<WorkExperienceForCv>>(this.workExperienceForCvRepository.findAll(),"Listed data");
    }

    @Override
    public Result add(WorkExperienceForCv experience) {
        if (findByExperienceId(experience.getCvId()).getData() != null){
            return new ErrorsResult(experience.getCvId() + "Same experience cannot repeat");
        }
        this.workExperienceForCvRepository.save(experience);
        return new SuccessResult("Added new education");
    }

    @Override
    public Result update(WorkExperienceForCv experience) {
        this.workExperienceForCvRepository.save(experience);
        return new SuccessResult("Updated experience");
    }

    @Override
    public Result delete(WorkExperienceForCv experience) {
        this.workExperienceForCvRepository.delete(experience);
        return new SuccessResult("Deleted experience");
    }

    @Override
    public DataResult<List<WorkExperienceForCv>> findByExperienceId(int id) {
        List<WorkExperienceForCv> cv = workExperienceForCvRepository.findByExperienceId(id);

        if (!cv.isEmpty())
            return new ErrorDataResult<>("This Work Experience Not Found");

        return new SuccessDataResult<List<WorkExperienceForCv>>(cv);
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
