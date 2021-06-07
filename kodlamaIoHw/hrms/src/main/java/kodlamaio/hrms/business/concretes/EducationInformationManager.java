package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EducationInformationService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EducationInformationForCVRepository;
import kodlamaio.hrms.entities.concretes.EducationInformationForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationInformationManager implements EducationInformationService {

    private EducationInformationForCVRepository educationInformationForCVRepository;

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
        if (findByEducationId(education.getCvId()).getData() != null) {
            return new ErrorsResult(education.getCvId() + "Same education cannot repeat");
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
    public Result delete(EducationInformationForCv education) {
        this.educationInformationForCVRepository.delete(education);
        return new SuccessResult("Deleted education");
    }

    @Override
    public DataResult<List<EducationInformationForCv>> findByEducationId(int id) {
        List<EducationInformationForCv> cv = educationInformationForCVRepository.findByEducationId(id);

        if (!cv.isEmpty())
            return new ErrorDataResult<>("This educationInformation Not Found");

        return new SuccessDataResult<List<EducationInformationForCv>>(cv);
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
