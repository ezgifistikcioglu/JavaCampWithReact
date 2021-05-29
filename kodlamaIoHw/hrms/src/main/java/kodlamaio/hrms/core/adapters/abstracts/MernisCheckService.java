package kodlamaio.hrms.core.adapters.abstracts;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface MernisCheckService {
    boolean checkIfRealPerson(JobSeeker jobSeeker);
}
