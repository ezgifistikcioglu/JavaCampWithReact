package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.dtos.CvDetailForJobSeekerDto;
import kodlamaio.hrms.entities.dtos.CvDto;

import java.util.List;

public interface CVService {
    DataResult<List<Cv>> getAll();

    DataResult<Cv> getByCvId(int cvId);

    DataResult<CvDetailForJobSeekerDto> getCvDetailForJobSeekerById(int cvId);

    Result add(CvDto cvDto);

    Result update(CvDto cvDto);

    Result delete(int id);
}
