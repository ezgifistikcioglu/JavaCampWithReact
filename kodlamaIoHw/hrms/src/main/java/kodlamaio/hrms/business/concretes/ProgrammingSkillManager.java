package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.dataAccess.abstracts.ProgrammingSkillForCvRepository;
import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;
import kodlamaio.hrms.entities.dtos.ProgrammingSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<ProgrammingSkillForCv> skillForCvs = this.skillForCvRepository.findAllByCv_CvId(id);

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
        Optional<ProgrammingSkillForCv> skillForCvs = this.skillForCvRepository.findById(programmingSkillDto.getSkillId());
        if (skillForCvs.isPresent()) {
            ProgrammingSkillForCv _skill = skillForCvs.get();
            _skill.setProgrammingName(programmingSkillDto.getProgrammingName());
            _skill.setProgrammingSkillLevel(programmingSkillDto.getProgrammingSkillLevel());
            _skill.setCreatedAt(programmingSkillDto.getCreatedAt());
            _skill.setCv(this.cvRepository.getOne(programmingSkillDto.getCvId()));
            skillForCvRepository.save(_skill);
            return  new SuccessDataResult<>("Skill updated successfully!");
        }else {
            return new ErrorDataResult<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Result delete(int id) {
        Optional<ProgrammingSkillForCv> skillForCvs = this.skillForCvRepository.findById(id);

        if (!skillForCvs.isPresent()) {
            return new ErrorDataResult<>("This programming skill not found");
        } else {
            this.skillForCvRepository.deleteById(id);
            return new SuccessResult("Deleted skill with id : " + id);
        }
    }

    @Override
    public DataResult<List<ProgrammingSkillForCv>> getByCv_CvId(int id) {
        List<ProgrammingSkillForCv> programmingSkillForCvList = skillForCvRepository.getByCv_CvId(id);
        if (programmingSkillForCvList.isEmpty()) {
            return new ErrorDataResult<>("This Programming Skill Not Found");
        } else {
            return new SuccessDataResult<>(programmingSkillForCvList,"Programming Skill information has been successfully listed for cvId");
        }
    }

    @Override
    public ProgrammingSkillForCv findById(int id) {
        Optional<ProgrammingSkillForCv> skillForCvs = this.skillForCvRepository.findById(id);

        return skillForCvs.isPresent() ? skillForCvs.get() : null;
    }
}
