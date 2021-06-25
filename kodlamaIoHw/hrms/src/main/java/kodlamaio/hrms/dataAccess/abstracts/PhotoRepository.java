package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.PhotoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<PhotoInfo, Integer> {

    PhotoInfo getById(int id);
    List<PhotoInfo> findAllById(int id);
}
