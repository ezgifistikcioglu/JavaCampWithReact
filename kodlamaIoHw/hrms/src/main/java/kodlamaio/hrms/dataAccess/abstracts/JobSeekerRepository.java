package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {
    JobSeeker findJobSeekerByTcNo(String tcNo);

    List<JobSeeker> findJobSeekersById(int id);

    JobSeeker getById(int id);

    JobSeeker findJobSeekerByFirstname(String firstName);

    JobSeeker findJobSeekerByLastname(String lastName);

    JobSeeker findJobSeekerByBirthYear(@NotNull int birthYear);
}
