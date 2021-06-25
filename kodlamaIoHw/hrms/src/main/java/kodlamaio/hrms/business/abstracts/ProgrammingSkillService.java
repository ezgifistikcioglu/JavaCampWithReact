package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;
import kodlamaio.hrms.entities.dtos.ProgrammingSkillDto;

import java.util.List;

public interface ProgrammingSkillService {
    DataResult<List<ProgrammingSkillForCv>> getAll();

    DataResult<List<ProgrammingSkillForCv>> findAllByCvId(int id);

    Result add(ProgrammingSkillDto programmingSkillDto);

    Result update(ProgrammingSkillDto programmingSkillDto);

    Result delete(int id);
}
