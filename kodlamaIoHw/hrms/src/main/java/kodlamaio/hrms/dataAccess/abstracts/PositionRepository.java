package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface PositionRepository extends JpaRepository<Position,Integer> {
  Position findByJobName(String jobName);

  Position findByCreatedDate(LocalDate createdDate);
}
