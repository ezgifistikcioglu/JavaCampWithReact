package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.PhotoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoInfo, Integer> {

	PhotoInfo getByJobSeekerId(int id);
	PhotoInfo getById(int id);
	
}
