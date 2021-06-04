package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;

import java.util.List;

public interface ProgrammingSkillService {
    DataResult<List<ProgrammingSkillForCv>> getAll();
    DataResult<List<ProgrammingSkillForCv>> findAllByCvId(int id);
    Result add(ProgrammingSkillForCv cv);
    Result update(ProgrammingSkillForCv cv);
    Result delete(ProgrammingSkillForCv cv);
}
