package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import kodlamaio.hrms.entities.dtos.LoginForEmployeeDto;

import java.util.List;

public interface SystemEmployeeService {

    DataResult<List<SystemEmployee>> getAllSystemEmployee();

    DataResult<List<SystemEmployee>> findAllById(int id);

    DataResult<SystemEmployee> getById(int id);

    Result addEmployee(LoginForEmployeeDto employeeDto);

    Result updateEmployee(SystemEmployee systemEmployee);

    Result deleteEmployee(int id);
}
