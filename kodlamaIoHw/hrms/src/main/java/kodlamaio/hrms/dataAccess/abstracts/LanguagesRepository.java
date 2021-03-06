package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.LanguagesForCv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LanguagesRepository extends JpaRepository<LanguagesForCv, Integer> {
    List<LanguagesForCv> findAllByLanguageId(int id);

    List<LanguagesForCv> findAllByCv_CvId(int id);

    List<LanguagesForCv> getByCv_CvId(int id);

    Optional<LanguagesForCv> getByLanguageId(int id);
}
