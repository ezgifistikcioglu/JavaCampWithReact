package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.SystemEmployeeRepository;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SystemEmployeeManager implements SystemEmployeeService {

    private final SystemEmployeeRepository systemEmployeeRepository;

    @Autowired
    public SystemEmployeeManager(SystemEmployeeRepository systemEmployeeRepository) {
        super();
        this.systemEmployeeRepository = systemEmployeeRepository;
    }

    @Override
    public Result add(int id) {
        Optional<SystemEmployee> employee = this.systemEmployeeRepository.findById(id);
        return new SuccessResult("Added employer: " + employee);
    }

    @Override
    public DataResult<List<SystemEmployee>> getAllSystemEmployee() {
        return new SuccessDataResult<>(this.systemEmployeeRepository.findAll(), "Listed data");
    }

    @Override
    public DataResult<SystemEmployee> getByUserId(int userId) {
        Optional<SystemEmployee> systemEmployee = this.systemEmployeeRepository.findById(userId);

        if (!systemEmployee.isPresent()) {
            return new ErrorDataResult<>("System employee not found");
        } else {
            return new SuccessDataResult<>(systemEmployee.get());
        }
    }

    @Override
    public Result addEmployer(SystemEmployee systemEmployee) {
        if (getByUserId(systemEmployee.getId()).getData() != null) {
            return new ErrorsResult("id: " + systemEmployee.getId() + "Employee first name: " + systemEmployee.getFirstName() + "Employee last name: " + systemEmployee.getLastName() + "Same employee cannot repeat");
        } else {
            this.systemEmployeeRepository.save(systemEmployee);
            return new SuccessResult("Added employee");
        }
    }

    @Override
    public Result updateEmployer(SystemEmployee systemEmployee) {
        if (getByUserId(systemEmployee.getId()).getData() != null) {
            return new ErrorsResult("id: " + systemEmployee.getId() + "Employee first name: " + systemEmployee.getFirstName() + "Employee last name: " + systemEmployee.getLastName() + "Same employee cannot repeat");
        } else {
            this.systemEmployeeRepository.save(systemEmployee);
            return new SuccessResult("Updated employee");
        }
    }

    @Override
    public Result deleteEmployer(SystemEmployee systemEmployee) {
        this.systemEmployeeRepository.delete(systemEmployee);
        return new SuccessResult("Deleted employee");
    }
}
