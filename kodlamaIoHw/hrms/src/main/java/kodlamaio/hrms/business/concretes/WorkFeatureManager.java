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
    public Result update(TypeOfWorkFeature workFeature) {
        Optional<TypeOfWorkFeature> workFeatureOptional = this.featureRepository.findById(workFeature.getWorkTypeId());

        if (!workFeatureOptional.isPresent()) {
            return new ErrorsResult("This type feature ( id " + workFeature.getWorkTypeId() + "-" + workFeature.getWorkTypeName() + " ) doesnt available!");
        } else {
            workFeatureOptional.get().setWorkTypeName(workFeature.getWorkTypeName());
            this.featureRepository.save(workFeatureOptional.get());
            return new SuccessResult("Type feature (" + workFeature.getWorkTypeName() + ") updated successfully.");
        }
    }

    @Override
    public Result delete(int id) {
        List<TypeOfWorkFeature> type = this.featureRepository.findAllByWorkTypeId(id);
        if (type.isEmpty()) {
            return new ErrorDataResult<>("This time feature not found");
        } else {
            this.featureRepository.deleteById(id);
            return new SuccessResult("Deleted time feature with id : " + id);
        }
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