package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.SystemEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemEmployeeRepository extends JpaRepository<SystemEmployee, Integer> {
    List<SystemEmployee> findAllByUserId(int id);

    SystemEmployee findByUserId(int id);
}
