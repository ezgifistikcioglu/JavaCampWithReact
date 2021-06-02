package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Position;

import java.time.LocalDate;
import java.util.List;

public interface PositionService {
    DataResult<List<Position>> getAll();
    DataResult<Position> getByJobName(String jobName);
    DataResult<Position> getByCreatedDate(LocalDate createdDate);
    Result add(Position position);
}
