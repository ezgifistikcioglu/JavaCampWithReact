package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SystemEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SystemEmployeeManager implements SystemEmployeeService {

    private SystemEmployeeRepository systemEmployeeRepository;

    @Autowired
    public SystemEmployeeManager(SystemEmployeeRepository systemEmployeeRepository) {
        super();
        this.systemEmployeeRepository = systemEmployeeRepository;
    }

    @Override
    public boolean giveConfirmation() {
        return false;
    }

    @Override
    public Result add(int id) {
        return new SuccessResult("Added employer");
    }
}