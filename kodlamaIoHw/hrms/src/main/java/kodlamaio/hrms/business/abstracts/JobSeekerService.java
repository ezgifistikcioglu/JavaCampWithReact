package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.LoginForJobSeekerDto;

import java.util.Date;
import java.util.List;

public interface JobSeekerService {
    DataResult<JobSeeker> getJobSeekerByTcNo(String tcNo);
    DataResult<JobSeeker> getJobSeekerByFirstname(String firstName);
    DataResult<JobSeeker> getJobSeekerByLastname(String lastName);
    DataResult<JobSeeker> getJobSeekerByBirthYear(Date birthYear);
    DataResult<List<JobSeeker>> getAll();
    Result add(JobSeeker jobSeeker);
    Result register(LoginForJobSeekerDto jobSeeker);
    Result isThereTCNo(String tcNo);
}
