package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.LoginForEmployerDto;

import java.util.List;

public interface EmployerService {

    DataResult<List<Employer>> getAll();

    Result addEmployer(Employer employer);
    Result updateEmployer(Employer employer);
    Result deleteEmployer(Employer employer);
    Result isEmailAvailable(String email);
    Result register(LoginForEmployerDto employerDto);
}
