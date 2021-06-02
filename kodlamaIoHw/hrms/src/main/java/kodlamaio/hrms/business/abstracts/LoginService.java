package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.User;

public interface LoginService {
    Result loginEmployer(Employer employer, String confirmPassword);
    Result loginJobSeeker(JobSeeker jobSeeker, String confirmPassword);
}
