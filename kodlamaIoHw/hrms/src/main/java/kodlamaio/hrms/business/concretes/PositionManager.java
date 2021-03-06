package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.PositionRepository;
import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PositionManager implements PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionManager(PositionRepository positionRepository) {
        super();
        this.positionRepository = positionRepository;
    }

    @Override
    public DataResult<List<Position>> getAll() {
        return new SuccessDataResult<>(this.positionRepository.findAll(), "Listed data");
    }

    @Override
    public DataResult<Position> findById(int id) {
        Optional<Position> position = this.positionRepository.findById(id);

        if (position.isPresent()) {
            return new SuccessDataResult<>(position.get());
        } else {
            return new ErrorDataResult<>("Position not found");

        }
    }

    @Override
    public DataResult<Position> getByJobName(String jobName) {
        return new SuccessDataResult<>(this.positionRepository.findByJobPositionName(jobName));
    }

    @Override
    public DataResult<Position> getByCreatedDate(LocalDate createdDate) {
        return new SuccessDataResult<>(this.positionRepository.findByCreatedDate(createdDate));
    }

    @Override
    public Result add(Position position) {
        if (getByJobName(position.getJobPositionName()).getData() != null) {
            return new ErrorsResult(position.getJobPositionName() + "Positions cannot repeat");
        } else {
            this.positionRepository.save(position);
            return new SuccessResult("Added position");
        }
    }
}
