package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    Position findByJobPositionName(String jobName);

    Position findByCreatedDate(LocalDate createdDate);

    Position getById(int id);
}
