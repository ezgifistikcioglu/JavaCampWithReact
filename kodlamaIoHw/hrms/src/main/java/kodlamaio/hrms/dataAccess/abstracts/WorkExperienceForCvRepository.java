package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.WorkExperienceForCv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface WorkExperienceForCvRepository extends JpaRepository<WorkExperienceForCv, Integer> {
    List<WorkExperienceForCv> findByExperienceId(int id);

    List<WorkExperienceForCv> findByExperienceIdOrderByBusinessLeavingDate(int idWithoutDesc);

    List<WorkExperienceForCv> findByExperienceIdOrderByBusinessLeavingDateDesc(int idWithDesc);
}
