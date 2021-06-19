package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.SystemEmployeeRepository;
import kodlamaio.hrms.entities.concretes.Advertisement;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemEmployeeManager implements SystemEmployeeService {

    private SystemEmployeeRepository systemEmployeeRepository;

    @Autowired
    public SystemEmployeeManager(SystemEmployeeRepository systemEmployeeRepository) {
        super();
        this.systemEmployeeRepository = systemEmployeeRepository;
    }

    public SystemEmployeeManager() {
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
    public DataResult<SystemEmployee> getById(int id) {
        Optional<SystemEmployee> systemEmployee = this.systemEmployeeRepository.findById(id);

        if (!systemEmployee.isPresent()) {
            return new ErrorDataResult<>("System employee not found");
        } else {
            return new SuccessDataResult<>(systemEmployee.get());
        }
    }

    @Override
    public Result addEmployee(SystemEmployee systemEmployee) {
        if (this.getById(systemEmployee.getId()).getData() != null) {
            return new ErrorsResult("id: " + systemEmployee.getId() + "Employee first name: " + systemEmployee.getFirstName() + "Employee last name: " + systemEmployee.getLastName() + "Same employee cannot repeat");
        } else {
            this.systemEmployeeRepository.save(systemEmployee);
            return new SuccessResult("Added Employee");
        }
    }

    public boolean addEmployer(Employer employer) {
        SystemEmployee systemEmployee = new SystemEmployee();
        if (systemEmployee.isApproved()) {
            systemEmployee.setUser(employer);
            systemEmployee.setApproved(true);
            return true;
        }else {
            systemEmployee.setApproved(false);
            return false;
        }
    }

    public boolean confirmAdvertisement(Advertisement advertisement) {
        SystemEmployee systemEmployee = new SystemEmployee();
        if (systemEmployee.isApproved()) {
            systemEmployee.setApproved(true);
            advertisement.setAdvertisementOpen(true);
            return true;
        }else {
            systemEmployee.setApproved(false);
            advertisement.setAdvertisementOpen(false);
            return false;
        }
    }

    @Override
    public Result updateEmployee(SystemEmployee systemEmployee) {
        if (this.getById(systemEmployee.getId()).getData() != null) {
            return new ErrorsResult("id: " + systemEmployee.getId() + "Employee first name: " + systemEmployee.getFirstName() + "Employee last name: " + systemEmployee.getLastName() + "Same employee cannot repeat");
        } else {
            this.systemEmployeeRepository.save(systemEmployee);
            return new SuccessResult("Updated employee");
        }
    }

    @Override
    public Result updateEmployer(Employer employer) {
        return null;
    }

    @Override
    public Result deleteEmployer(int id) {
        this.systemEmployeeRepository.deleteById(id);
        return new SuccessResult("Deleted employee");
    }

    @Override
    public Result deleteEmployee(int id) {
        return null;
    }
}
