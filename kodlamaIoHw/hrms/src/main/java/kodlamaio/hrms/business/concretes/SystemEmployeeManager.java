package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.SystemEmployeeRepository;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import kodlamaio.hrms.entities.dtos.LoginForEmployeeDto;
import lombok.NoArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class SystemEmployeeManager implements SystemEmployeeService {
    private SystemEmployeeRepository systemEmployeeRepository;
    private UserService userService;

    @Autowired
    public SystemEmployeeManager(SystemEmployeeRepository systemEmployeeRepository, UserService userService) {
        super();
        this.systemEmployeeRepository = systemEmployeeRepository;
        this.userService = userService;
    }

    private boolean checkPasswordMatch(String password, String confirmPassword) {
        boolean resultValidate = password.length() >= 4;
        if (!resultValidate) {
            System.out.println("Password must not be less than 4 characters, please check your password: " + password);
            return false;
        }
        return password.equals(confirmPassword);
    }

    @Override
    public Result addEmployee(LoginForEmployeeDto employeeDto) {
        var checkEmail = userService.checkUserByEmail(employeeDto.getEmail());

        if (checkEmail.isSuccess()) {
            return new ErrorsResult("Error : employee user email : " + employeeDto.getEmail() + " already exists!");
        } else {

            if (!checkPasswordMatch(employeeDto.getPassword(), employeeDto.getConfirmPassword())) {
                return new ErrorsResult("Error : Employee password and confirmPassword does not match! : " + employeeDto.getPassword() + "-" + employeeDto.getConfirmPassword());
            } else {
                if (getById(employeeDto.getId()).getData() != null) {
                    return new ErrorsResult(employeeDto.getId() + "Same employee cannot repeat");
                } else {
                    SystemEmployee employee = new SystemEmployee();
                    employee.setEmail(employeeDto.getEmail());
                    employee.setFirstName(employeeDto.getFirstName());
                    employee.setLastName(employeeDto.getLastName());
                    employee.setPassword(employeeDto.getPassword());
                    employee.setConfirmPassword(employeeDto.getConfirmPassword());
                    employee.setSystemUser(true);
                    this.systemEmployeeRepository.save(employee);

                    return new SuccessResult("Employee registration completed successfully.");
                }
            }
        }
    }

    @Override
    public DataResult<List<SystemEmployee>> getAllSystemEmployee() {
        return new SuccessDataResult<>(this.systemEmployeeRepository.findAll(), "Listed data");
    }

    @Override
    public DataResult<SystemEmployee> getById(int id) {
        Optional<SystemEmployee> optionalSystemEmployee = this.systemEmployeeRepository.findById(id);

        if (!optionalSystemEmployee.isPresent()) {
            return new ErrorDataResult<>("System employee not found");
        } else {
            return new SuccessDataResult<>(optionalSystemEmployee.get());
        }
    }

    @Override
    public DataResult<List<SystemEmployee>> findAllById(int id) {
        List<SystemEmployee> systemEmployee = this.systemEmployeeRepository.findAllByUserId(id);

        if (systemEmployee.isEmpty()) {
            return new ErrorDataResult<>("Information for these system employees could not be found.");
        } else {
            return new SuccessDataResult<>(systemEmployee, "System employee added successfully");
        }
    }

    @Override
    public Result updateEmployee(SystemEmployee systemEmployee) {
        Optional<SystemEmployee> optionalSystemEmployee = this.systemEmployeeRepository.findById(systemEmployee.getUserId());

        if (!optionalSystemEmployee.isPresent()) {
            return new ErrorsResult("This employee ( id " + systemEmployee.getUserId() + "-" + systemEmployee.getEmail() + " ) doesnt available!");
        } else {
            if (!systemEmployee.getPassword().equals(systemEmployee.getConfirmPassword())) {
                return new ErrorsResult("Employee password and confirmPassword values must be same!");
            } else {

                optionalSystemEmployee.get().setUserId(systemEmployee.getUserId());
                optionalSystemEmployee.get().setEmail(systemEmployee.getEmail());
                optionalSystemEmployee.get().setPassword(systemEmployee.getPassword());
                optionalSystemEmployee.get().setConfirmPassword(systemEmployee.getConfirmPassword());
                optionalSystemEmployee.get().setFirstName(systemEmployee.getFirstName());
                optionalSystemEmployee.get().setLastName(systemEmployee.getLastName());

                this.systemEmployeeRepository.save(optionalSystemEmployee.get());
                return new SuccessResult("Employee (" + systemEmployee.getUserId() + ") updated successfully.");
            }
        }
    }

    @Override
    public Result deleteEmployee(int id) {
        this.systemEmployeeRepository.deleteById(id);
        return new SuccessResult("Deleted employee");
    }
}
