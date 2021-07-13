package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.SocialMediaForCv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SocialMediaForCvRepository extends JpaRepository<SocialMediaForCv, Integer> {
    List<SocialMediaForCv> findAllById(int id);
    List<SocialMediaForCv> getByCv_CvId(int id);
}
