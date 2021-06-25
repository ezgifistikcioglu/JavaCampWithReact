package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.dataAccess.abstracts.ProgrammingSkillForCvRepository;
import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;
import kodlamaio.hrms.entities.dtos.ProgrammingSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingSkillManager implements ProgrammingSkillService {

    private final ProgrammingSkillForCvRepository skillForCvRepository;
    private final CVRepository cvRepository;

    @Autowired
    public ProgrammingSkillManager(ProgrammingSkillForCvRepository skillForCvRepository, CVRepository cvRepository) {
        super();
        this.skillForCvRepository = skillForCvRepository;
        this.cvRepository = cvRepository;
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
    public Result add(ProgrammingSkillDto programmingSkillDto) {
        if (findAllByCvId(programmingSkillDto.getSkillId()).getData() != null) {
            return new ErrorsResult(programmingSkillDto.getSkillId() + "Same skill cannot repeat");
        } else {
            ProgrammingSkillForCv programmingSkillForCv = new ProgrammingSkillForCv();
            programmingSkillForCv.setProgrammingName(programmingSkillDto.getProgrammingName());
            programmingSkillForCv.setProgrammingSkillLevel(programmingSkillDto.getProgrammingSkillLevel());
            programmingSkillForCv.setCreatedAt(programmingSkillDto.getCreatedAt());
            programmingSkillForCv.setCv(this.cvRepository.getOne(programmingSkillDto.getCvId()));
            this.skillForCvRepository.save(programmingSkillForCv);
            return new SuccessResult("Added new skill");
        }
    }

    @Override
    public Result update(ProgrammingSkillDto programmingSkillDto) {
        Optional<ProgrammingSkillForCv> skillForCv = this.skillForCvRepository.getById(programmingSkillDto.getSkillId());
        if (!skillForCv.isPresent()) {
            return new ErrorsResult("This skill ( id " + programmingSkillDto.getSkillId() + " ) doesnt available!");
        } else {
            skillForCv.get().setProgrammingName(programmingSkillDto.getProgrammingName());
            skillForCv.get().setProgrammingSkillLevel(programmingSkillDto.getProgrammingSkillLevel());
            skillForCv.get().setCreatedAt(programmingSkillDto.getCreatedAt());
            skillForCv.get().setCv(this.cvRepository.getOne(programmingSkillDto.getCvId()));
            this.skillForCvRepository.save(skillForCv.get());
            return new SuccessResult("Updated new skill");
        }
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
