package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EducationInformationService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EducationInformationForCVRepository;
import kodlamaio.hrms.entities.concretes.EducationInformationForCv;
import kodlamaio.hrms.entities.concretes.WorkExperienceForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationInformationManager implements EducationInformationService {

    private final EducationInformationForCVRepository educationInformationForCVRepository;

    @Autowired
    public EducationInformationManager(EducationInformationForCVRepository educationInformationForCVRepository) {
        super();
        this.educationInformationForCVRepository = educationInformationForCVRepository;
    }

    @Override
    public DataResult<List<EducationInformationForCv>> getAll() {
        return new SuccessDataResult<List<EducationInformationForCv>>(this.educationInformationForCVRepository.findAll(), "Listed data");
    }

    @Override
    public Result add(EducationInformationForCv education) {
        if (findByEducationId(education.getEducationId()).getData() != null) {
            return new ErrorsResult(education.getEducationId() + "There is no such education");
        } else {
            this.educationInformationForCVRepository.save(education);
            return new SuccessResult("Added new education");
        }
    }

    @Override
    public Result update(EducationInformationForCv education) {
        this.educationInformationForCVRepository.save(education);
        return new SuccessResult("Updated education");
    }

    @Override
    public Result delete(int id) {
        Optional<EducationInformationForCv> educationInformationForCv = this.educationInformationForCVRepository.findById(id);

        if (!educationInformationForCv.isPresent()) {
            return new ErrorDataResult<>("Education not found");
        } else {
        this.educationInformationForCVRepository.deleteById(id);
        return new SuccessResult("Deleted education");
        }
    }

    @Override
    public DataResult<List<EducationInformationForCv>> findByEducationId(int id) {
        List<EducationInformationForCv> educationInformationForCvs = educationInformationForCVRepository.findByEducationId(id);

        if (educationInformationForCvs.isEmpty()) {
            return new ErrorDataResult<>("This education information not found");

        } else {
            return new SuccessDataResult<>(educationInformationForCvs, "This education information has been successfully added");
        }
    }

    @Override
    public DataResult<List<EducationInformationForCv>> findByEducationIdOrderBySchoolGraduationDate(int idWithoutDesc) {
        return new SuccessDataResult<>(educationInformationForCVRepository.findByEducationIdOrderBySchoolGraduationDate(idWithoutDesc));
    }

    @Override
    public DataResult<List<EducationInformationForCv>> findByEducationIdOrderBySchoolGraduationDateDesc(int idWithDesc, Sort.Direction direction) {
        List<EducationInformationForCv> jobSeekerCVEducations = direction.isAscending()
                ? educationInformationForCVRepository.findByEducationIdOrderBySchoolGraduationDate(idWithDesc)
                : educationInformationForCVRepository.findByEducationIdOrderBySchoolGraduationDateDesc(idWithDesc);

        return new SuccessDataResult<>(jobSeekerCVEducations);
    }
}
