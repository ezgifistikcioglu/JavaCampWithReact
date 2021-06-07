package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.ProgrammingSkillForCvRepository;
import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammingSkillManager implements ProgrammingSkillService {

    private ProgrammingSkillForCvRepository skillForCvRepository;

    @Autowired
    public ProgrammingSkillManager(ProgrammingSkillForCvRepository skillForCvRepository) {
        super();
        this.skillForCvRepository = skillForCvRepository;
    }

    @Override
    public DataResult<List<ProgrammingSkillForCv>> getAll() {
        return new SuccessDataResult<List<ProgrammingSkillForCv>>(this.skillForCvRepository.findAll(), "Listed data");
    }

    @Override
    public DataResult<List<ProgrammingSkillForCv>> findAllByCvId(int id) {
        return new SuccessDataResult<List<ProgrammingSkillForCv>>(this.skillForCvRepository.findAllByCvId(id), "Added skill");
    }

    @Override
    public Result add(ProgrammingSkillForCv cv) {
        if (findAllByCvId(cv.getCvId()).getData() != null) {
            return new ErrorsResult(cv.getCvId() + "Same skill cannot repeat");
        } else {
            this.skillForCvRepository.save(cv);
            return new SuccessResult("Added new skill");
        }
    }

    @Override
    public Result update(ProgrammingSkillForCv cv) {
        this.skillForCvRepository.save(cv);
        return new SuccessResult("Updated skill");
    }

    @Override
    public Result delete(ProgrammingSkillForCv cv) {
        this.skillForCvRepository.delete(cv);
        return new SuccessResult("Deleted skill");
    }
}
