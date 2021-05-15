package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.PositionService;
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
    public List<Position> getAll() {
        return this.positionRepository.findAll();
    }
}
