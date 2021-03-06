package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.LoginForEmployerDto;

import java.util.List;

public interface EmployerService {

    DataResult<List<Employer>> getAll();

    DataResult<Employer> getById(int id);

    Result updateEmployer(Employer employer);

    Result deleteEmployer(int id);

    Result isEmailAvailable(String email);

    Result register(LoginForEmployerDto employerDto);

    Result confirmEmployer(LoginForEmployerDto employerDto);

    DataResult<List<Employer>> getAllWaitApproveEmployerList();

    DataResult<List<Employer>> getAllActiveAndApprovedEmployerList();
}
