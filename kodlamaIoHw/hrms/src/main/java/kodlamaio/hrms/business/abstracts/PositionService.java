package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Position;

import java.util.List;

public interface PositionService {
    DataResult<List<Position>> getAll();
    DataResult<Position> getByJobName(String jobName);
    Result add(Position position);
}
