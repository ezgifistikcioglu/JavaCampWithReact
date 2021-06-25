package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.TypeOfWorkFeature;

import java.util.List;

public interface WorkFeatureService {
    Result add(TypeOfWorkFeature workFeature);
    Result update(TypeOfWorkFeature workFeature);
    Result delete(int id);
    DataResult<TypeOfWorkFeature> findByWorkTypeId(int id);
    DataResult<List<TypeOfWorkFeature>> findByWorkTypeNameContains(String name);
    DataResult<List<TypeOfWorkFeature>> getAll();
}
