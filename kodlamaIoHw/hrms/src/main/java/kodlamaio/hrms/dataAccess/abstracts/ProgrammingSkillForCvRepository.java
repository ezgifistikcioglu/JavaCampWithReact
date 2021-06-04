package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammingSkillForCvRepository extends JpaRepository<ProgrammingSkillForCv, Integer> {
    List<ProgrammingSkillForCv> findByCvId(int id);
}
