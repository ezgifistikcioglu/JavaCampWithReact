package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.WorkFeatureService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.WorkFeatureRepository;
import kodlamaio.hrms.entities.concretes.TypeOfWorkFeature;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkFeatureManager implements WorkFeatureService {
    private final WorkFeatureRepository featureRepository;

    public WorkFeatureManager(WorkFeatureRepository featureRepository) {
        super();
        this.featureRepository = featureRepository;
    }

    @Override
    public Result add(TypeOfWorkFeature workFeature) {
        this.featureRepository.save(workFeature);
        return new SuccessResult("Added type feature");
    }

    @Override
    public DataResult<TypeOfWorkFeature> findByWorkTypeId(int id) {
        Optional<TypeOfWorkFeature> typeOfWorkFeature = this.featureRepository.findById(id);

        if (!typeOfWorkFeature.isPresent()) {
            return new ErrorDataResult<>("Type of work not found");
        } else {
            return new SuccessDataResult<>(typeOfWorkFeature.get());
        }
    }

    @Override
    public DataResult<List<TypeOfWorkFeature>> findByWorkTypeNameContains(String name) {
        return null;
    }

    @Override
    public DataResult<List<TypeOfWorkFeature>> getAll() {
        return new SuccessDataResult<>(this.featureRepository.findAll(), "Listed work type data");
    }
}