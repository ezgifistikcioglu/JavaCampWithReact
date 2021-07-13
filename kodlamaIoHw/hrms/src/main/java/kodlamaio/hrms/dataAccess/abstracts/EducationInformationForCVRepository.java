package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.EducationInformationForCv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface EducationInformationForCVRepository extends JpaRepository<EducationInformationForCv, Integer> {
    List<EducationInformationForCv> findByEducationId(int id);

    List<EducationInformationForCv> getByCv_CvId(int id);

    EducationInformationForCv getByEducationId(int id);

    List<EducationInformationForCv> findByEducationIdOrderBySchoolGraduationDate(int idWithoutDesc);

    List<EducationInformationForCv> findByEducationIdOrderBySchoolGraduationDateDesc(int idWithDesc);
}
