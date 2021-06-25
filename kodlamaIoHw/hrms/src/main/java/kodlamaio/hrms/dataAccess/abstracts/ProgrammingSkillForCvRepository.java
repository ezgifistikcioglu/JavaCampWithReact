package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProgrammingSkillForCvRepository extends JpaRepository<ProgrammingSkillForCv, Integer> {
    List<ProgrammingSkillForCv> findAllById(int id);
    Optional<ProgrammingSkillForCv> getById(int id);
}
