package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.SystemEmployee;

import java.util.List;

public interface SystemEmployeeService {
    Result add(int id);

    DataResult<List<SystemEmployee>> getAllSystemEmployee();

    Result addEmployer(SystemEmployee systemEmployee);
    Result updateEmployer(SystemEmployee systemEmployee);
    Result deleteEmployer(SystemEmployee systemEmployee);
}
