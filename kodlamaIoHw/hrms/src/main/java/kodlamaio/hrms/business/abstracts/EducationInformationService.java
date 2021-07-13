package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EducationInformationForCv;
import kodlamaio.hrms.entities.dtos.EducationDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EducationInformationService {
    DataResult<List<EducationInformationForCv>> getAll();

    DataResult<EducationInformationForCv> getByEducationId(int id);

    DataResult<List<EducationInformationForCv>> getByCv_CvId(int id);

    Result add(EducationDto educationDto);

    Result update(EducationDto educationDto);

    Result delete(int id);

    DataResult<List<EducationInformationForCv>> findByEducationId(int id);

    DataResult<List<EducationInformationForCv>> findByEducationIdOrderBySchoolGraduationDate(int idWithoutDesc);

    DataResult<List<EducationInformationForCv>> findByEducationIdOrderBySchoolGraduationDateDesc(int idWithDesc, Sort.Direction direction);
}
