package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.WorkTimeFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkTimeRepository extends JpaRepository<WorkTimeFeature, Integer> {
    WorkTimeFeature findByWorkTimeId(int id);

    List<WorkTimeFeature> findByWorkTimeNameContains(String name);
}
