package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.dtos.CvDetailForJobSeekerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CVRepository extends JpaRepository<Cv, Integer> {
    Cv findByCvId(int id);

    @Query("Select new kodlamaio.hrms.entities.dtos.CvDetailForJobSeekerDto(c.cvId,c.coverLetter,j) From Cv c JOIN c.jobSeeker j Where c.cvId=:id")
    CvDetailForJobSeekerDto getCvDetailForJobSeekerById(int id);
}
