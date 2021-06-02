package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface JobSeekerRepository extends JpaRepository<JobSeeker,Integer> {
    JobSeeker findJobSeekerByTcNo(String tcNo);

    JobSeeker findJobSeekerByFirstname(String firstName);

    JobSeeker findJobSeekerByLastname(String lastName);

    JobSeeker findJobSeekerByBirthYear(Date birthYear);
}
