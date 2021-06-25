package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.WorkTimeService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.WorkTimeRepository;
import kodlamaio.hrms.entities.concretes.WorkTimeFeature;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkTimeManager implements WorkTimeService {
    private final WorkTimeRepository workTimeRepository;

    public WorkTimeManager(WorkTimeRepository workTimeRepository) {
        super();
        this.workTimeRepository = workTimeRepository;
    }

    @Override
    public Result add(WorkTimeFeature timeFeature) {
        this.workTimeRepository.save(timeFeature);
        return new SuccessResult("Added time feature");
    }

    @Override
    public Result update(WorkTimeFeature timeFeature) {
        Optional<WorkTimeFeature> feature = this.workTimeRepository.findById(timeFeature.getWorkTimeId());

        if (!feature.isPresent()) {
            return new ErrorsResult("This time feature ( id " + timeFeature.getWorkTimeId() + "-" + timeFeature.getWorkTimeName() + " ) doesnt available!");
        } else {
            feature.get().setWorkTimeName(timeFeature.getWorkTimeName());
            this.workTimeRepository.save(feature.get());
            return new SuccessResult("Time feature (" + timeFeature.getWorkTimeName() + ") updated successfully.");
        }
    }

    @Override
    public Result delete(int id) {
        List<WorkTimeFeature> workTimeFeatures = this.workTimeRepository.findAllByWorkTimeId(id);
        if (workTimeFeatures.isEmpty()) {
            return new ErrorDataResult<>("This time feature not found");
        } else {
            this.workTimeRepository.deleteById(id);
            return new SuccessResult("Deleted time feature with id : " + id);
        }
    }

    @Override
    public DataResult<WorkTimeFeature> findByWorkTimeId(int id) {
        Optional<WorkTimeFeature> workTimeFeature = this.workTimeRepository.findById(id);

        if (!workTimeFeature.isPresent()) {
            return new ErrorDataResult<>("Work time not found");
        } else {
            return new SuccessDataResult<>(workTimeFeature.get());
        }
    }

    @Override
    public DataResult<List<WorkTimeFeature>> findByWorkTimeNameContains(String name) {
        return null;
    }

    @Override
    public DataResult<List<WorkTimeFeature>> getAll() {
        return new SuccessDataResult<>(this.workTimeRepository.findAll(), "Listed work time data");
    }
}
