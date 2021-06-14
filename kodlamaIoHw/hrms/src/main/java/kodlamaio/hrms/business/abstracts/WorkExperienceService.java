package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.WorkExperienceForCv;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface WorkExperienceService {
    DataResult<List<WorkExperienceForCv>> getAll();

    Result add(WorkExperienceForCv experience);

    Result update(WorkExperienceForCv experience);

    Result delete(int id);

    DataResult<List<WorkExperienceForCv>> findByExperienceId(int id);

    DataResult<List<WorkExperienceForCv>> findByExperienceIdOrderByBusinessLeavingDate(int idWithoutDesc);

    DataResult<List<WorkExperienceForCv>> findByExperienceIdOrderByBusinessLeavingDateDesc(int idWithDesc, Sort.Direction direction);
}
