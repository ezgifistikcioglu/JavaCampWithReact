package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EducationInformationService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.dataAccess.abstracts.EducationInformationForCVRepository;
import kodlamaio.hrms.entities.concretes.EducationInformationForCv;
import kodlamaio.hrms.entities.concretes.WorkTimeFeature;
import kodlamaio.hrms.entities.dtos.EducationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EducationInformationManager implements EducationInformationService {

    private final EducationInformationForCVRepository educationInformationForCVRepository;
    private final CVRepository cvRepository;

    @Autowired
    public EducationInformationManager(EducationInformationForCVRepository educationInformationForCVRepository, CVRepository cvRepository) {
        super();
        this.educationInformationForCVRepository = educationInformationForCVRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    public DataResult<List<EducationInformationForCv>> getAll() {
        List<EducationInformationForCv> educationInformationForCvs = this.educationInformationForCVRepository.findAll();
        return new SuccessDataResult<>(educationInformationForCvs, "Education information listed successfully");
    }

    @Override
    public DataResult<EducationInformationForCv> getByEducationId(int id) {
        if (!this.educationInformationForCVRepository.existsById(id)) {
            return new ErrorDataResult<>("Error! : Education could not be found!");
        }
        return new SuccessDataResult<>(this.educationInformationForCVRepository.getByEducationId(id));
    }

    @Override
    public DataResult<List<EducationInformationForCv>> getByCv_CvId(int id) {
        List<EducationInformationForCv> educationInformationForCvs = educationInformationForCVRepository.getByCv_CvId(id);

        if (educationInformationForCvs.isEmpty()) {
            return new ErrorDataResult<>("This education information not found");

        } else {
            return new SuccessDataResult<>(educationInformationForCvs, "This education information has been successfully listed for cvId");
        }
    }

    @Override
    public Result add(EducationDto educationDto) {
        if (findByEducationId(educationDto.getEducationId()).getData() != null) {
            return new ErrorsResult(educationDto.getEducationId() + "There is no such education");
        } else {
            EducationInformationForCv educationInformation = new EducationInformationForCv();
            customField(educationDto, educationInformation);
            return new SuccessResult("Added new education");
        }
    }

    @Override
    public Result update(EducationDto educationDto) {
        EducationInformationForCv educationInformationForCv = this.educationInformationForCVRepository.getByEducationId(educationDto.getEducationId());
        if (educationInformationForCv == null) {
            return new ErrorsResult("This time education ( id " + Objects.requireNonNull(educationInformationForCv).getEducationId() + " ) doesnt available!");
        } else {
            customField(educationDto, educationInformationForCv);
            return new SuccessResult("Updated education");
        }
    }

    private void customField(EducationDto educationDto, EducationInformationForCv educationInformation) {
        educationInformation.setSchoolName(educationDto.getSchoolName());
        educationInformation.setCv(this.cvRepository.getOne(educationDto.getCvId()));
        educationInformation.setSchoolDepartmentName(educationDto.getSchoolDepartmentName());
        educationInformation.setSchoolGraduationDate(educationDto.getSchoolGraduationDate());
        educationInformation.setSchoolStartDate(educationDto.getSchoolStartDate());
        this.educationInformationForCVRepository.save(educationInformation);
        System.out.println("education" + educationDto);
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
