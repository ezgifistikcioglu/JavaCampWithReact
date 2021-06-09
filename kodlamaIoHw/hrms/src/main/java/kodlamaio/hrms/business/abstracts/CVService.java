package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Cv;

import java.util.List;

public interface CVService {
    DataResult<List<Cv>> getAll();
    DataResult<Cv> getByCvId(int cvId);
    Result add(Cv cv);
    Result update(Cv cv);
    Result delete(Cv cv);
}
