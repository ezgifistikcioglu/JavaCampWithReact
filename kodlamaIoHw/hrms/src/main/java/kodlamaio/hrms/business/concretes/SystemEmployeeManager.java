package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SystemEmployeeRepository;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SystemEmployeeManager implements SystemEmployeeService {

    private SystemEmployeeRepository systemEmployeeRepository;

    @Autowired
    public SystemEmployeeManager(SystemEmployeeRepository systemEmployeeRepository) {
        super();
        this.systemEmployeeRepository = systemEmployeeRepository;
    }

    @Override
    public Result add(int id) {
        return new SuccessResult("Added employer");
    }

    @Override
    public DataResult<List<SystemEmployee>> getAllSystemEmployee() {
        return null;
    }

    @Override
    public Result addEmployer(SystemEmployee systemEmployee) {
        return null;
    }

    @Override
    public Result updateEmployer(SystemEmployee systemEmployee) {
        return null;
    }

    @Override
    public Result deleteEmployer(SystemEmployee systemEmployee) {
        return null;
    }
}
