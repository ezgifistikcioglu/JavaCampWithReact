package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.WorkTimeFeature;

import java.util.List;

public interface WorkTimeService {
    Result add(WorkTimeFeature timeFeature);
    Result update(WorkTimeFeature timeFeature);
    Result delete(int id);
    DataResult<WorkTimeFeature> findByWorkTimeId(int id);
    DataResult<List<WorkTimeFeature>> findByWorkTimeNameContains(String name);
    DataResult<List<WorkTimeFeature>> getAll();
}
