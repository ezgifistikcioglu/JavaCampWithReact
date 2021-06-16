package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.WorkTimeService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.WorkTimeRepository;
import kodlamaio.hrms.entities.concretes.TypeOfWorkFeature;
import kodlamaio.hrms.entities.concretes.WorkTimeFeature;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkTimeManager implements WorkTimeService {
    private  final WorkTimeRepository workTimeRepository;

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
