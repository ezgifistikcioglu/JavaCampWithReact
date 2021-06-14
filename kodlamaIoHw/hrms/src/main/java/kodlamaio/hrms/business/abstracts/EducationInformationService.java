package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EducationInformationForCv;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EducationInformationService {
    DataResult<List<EducationInformationForCv>> getAll();

    Result add(EducationInformationForCv education);

    Result update(EducationInformationForCv education);

    Result delete(int id);

    DataResult<List<EducationInformationForCv>> findByEducationId(int id);

    DataResult<List<EducationInformationForCv>> findByEducationIdOrderBySchoolGraduationDate(int idWithoutDesc);

    DataResult<List<EducationInformationForCv>> findByEducationIdOrderBySchoolGraduationDateDesc(int idWithDesc, Sort.Direction direction);
}
