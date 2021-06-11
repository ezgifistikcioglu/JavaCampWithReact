package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.LanguagesForCv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguagesRepository extends JpaRepository<LanguagesForCv, Integer> {
    List<LanguagesForCv> findAllByLanguageId(int id);

    LanguagesForCv getByLanguageId(int id);
}
