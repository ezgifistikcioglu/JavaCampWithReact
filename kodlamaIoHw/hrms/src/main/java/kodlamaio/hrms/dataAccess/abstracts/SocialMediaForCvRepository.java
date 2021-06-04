package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.SocialMediaForCv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialMediaForCvRepository extends JpaRepository<SocialMediaForCv, Integer> {
    List<SocialMediaForCv> findByCvId(int id);
}
