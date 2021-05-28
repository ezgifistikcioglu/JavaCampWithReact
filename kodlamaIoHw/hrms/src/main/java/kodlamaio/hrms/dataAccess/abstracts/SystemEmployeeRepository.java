package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.SystemEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemEmployeeRepository extends JpaRepository<SystemEmployee,Integer> {

}
