package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CVRepository extends JpaRepository<Cv, Integer> {
    Optional<Cv> getByCvId(int id);
}
