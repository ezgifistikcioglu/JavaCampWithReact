package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.PositionRepository;
import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionManager implements PositionService {

    private PositionRepository positionRepository;

    @Autowired
    public PositionManager(PositionRepository positionRepository) {
        super();
        this.positionRepository = positionRepository;
    }

    @Override
    public DataResult<List<Position>> getAll() {
        return new SuccessDataResult<List<Position>>(this.positionRepository.findAll(),"Listed data");
    }

    @Override
    public DataResult<Position> getByJobName(String jobName) {
        return new SuccessDataResult<Position>(this.positionRepository.findByJobName(jobName));
    }

    @Override
    public Result add(Position position) {
        if (getByJobName(position.getJobName()).getData() != null){
            return new ErrorsResult(position.getJobName() + "Positions cannot repeat");
        }
        this.positionRepository.save(position);
        return new SuccessResult("Added position");
    }
}
