package kodlamaio.hrms.core.adapters.abstracts;

import kodlamaio.hrms.entities.dtos.LoginForJobSeekerDto;

public interface MernisCheckService {
    boolean checkIfRealPerson(LoginForJobSeekerDto jobSeeker);
}
