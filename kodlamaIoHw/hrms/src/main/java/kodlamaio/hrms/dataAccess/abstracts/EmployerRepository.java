package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Position,Integer> {

}
