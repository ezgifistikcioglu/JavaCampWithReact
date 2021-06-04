package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CV;

import java.util.List;

public interface CVService {
    DataResult<List<CV>> getAll();
    DataResult<CV> getByCvId(int cvId);
    Result add(CV cv);
    Result update(CV cv);
    Result delete(CV cv);
}
