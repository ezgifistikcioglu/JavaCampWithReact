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

    private final ProgrammingSkillForCvRepository skillForCvRepository;

    @Autowired
    public ProgrammingSkillManager(ProgrammingSkillForCvRepository skillForCvRepository) {
        super();
        this.skillForCvRepository = skillForCvRepository;
    }

    @Override
    public DataResult<List<ProgrammingSkillForCv>> getAll() {
        return new SuccessDataResult<>(this.skillForCvRepository.findAll(), "Listed data");
    }

    @Override
    public DataResult<List<ProgrammingSkillForCv>> findAllByCvId(int id) {
        List<ProgrammingSkillForCv> skillForCvs = this.skillForCvRepository.findAllById(id);

        if (skillForCvs.isEmpty()) {
            return new ErrorDataResult<>("These skills were not found on cv.");

        } else {
            return new SuccessDataResult<>(skillForCvs, "Skills have been successfully added");
        }
    }

    @Override
    public Result add(ProgrammingSkillForCv cv) {
        if (findAllByCvId(cv.getId()).getData() != null) {
            return new ErrorsResult(cv.getId() + "Same skill cannot repeat");
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
    public Result delete(int id) {
        List<ProgrammingSkillForCv> skillForCvs = this.skillForCvRepository.findAllById(id);

        if (skillForCvs.isEmpty()) {
            return new ErrorDataResult<>("This programming skill not found");
        } else {
            this.skillForCvRepository.deleteById(id);
            return new SuccessResult("Deleted skill");
        }
    }
}
