package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.SystemEmployee;

import java.util.List;

public interface SystemEmployeeService {
    Result add(int id);

    DataResult<List<SystemEmployee>> getAllSystemEmployee();

    DataResult<SystemEmployee> getById(int id);

    Result addEmployee(SystemEmployee systemEmployee);

    Result updateEmployee(SystemEmployee systemEmployee);

    Result updateEmployer(Employer employer);

    Result deleteEmployer(int id);

    Result deleteEmployee(int id);
}
