package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmployerRepository;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import kodlamaio.hrms.entities.dtos.LoginForEmployerDto;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployerManager implements EmployerService {

    private EmployerRepository employerRepository;
    private UserService userService;
    private EmailVerificationService emailVerificationService;
    private SystemEmployeeService systemEmployeeService;

    @Autowired
    public EmployerManager(EmployerRepository employerRepository, UserService userService, EmailVerificationService emailVerificationService, SystemEmployeeService systemEmployeeService) {
        super();
        this.employerRepository = employerRepository;
        this.userService = userService;
        this.emailVerificationService = emailVerificationService;
        this.systemEmployeeService = systemEmployeeService;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerRepository.findAll(),"Listed data");
    }

    @Override
    public Result addEmployer(Employer employer) {
        this.employerRepository.save(employer);
        return new SuccessResult("Added employer");
    }

    @Override
    public Result updateEmployer(Employer employer) {
        this.employerRepository.save(employer);
        return new SuccessResult("Updated employer");
    }

    @Override
    public Result deleteEmployer(Employer employer) {
        this.employerRepository.delete(employer);
        return new SuccessResult("Deleted employer");
    }

    @Override
    public Result isEmailAvailable(String email) {
        return employerRepository.findByEmail(email).equals(Optional.empty()) ? new SuccessResult("Successful email for employer") : new ErrorsResult("An employer with this email already exists");
    }

    @Override
    public Result register(LoginForEmployerDto employerDto) {
        var res = BusinessRules.run(
                userService.checkByEmail(employerDto.getEmail()), checkPasswordMatch(employerDto.getPassword(),employerDto.getConfirmPassword()),
               isCorporateEmail(employerDto.getEmail(), employerDto.getWebAddress()),isEmailAvailable(employerDto.getEmail()));

        if (!Objects.requireNonNull(res).isSuccess()){
            return res;
        }
        Employer employer = new Employer();
        employer.setEmail(employerDto.getEmail());
        employer.setCompanyName(employerDto.getCompanyName());
        employer.setWebAddress(employerDto.getWebAddress());
        employer.setTelephoneNumber(employerDto.getTelephoneNumber());
        employer.setPassword(employerDto.getPassword());
        employer.setConfirmPassword(employerDto.getConfirmPassword());
        addEmployer(employer);

        emailVerificationService.sendActivationCode(employer,employer.getEmail());
        SystemEmployee systemEmployee = new SystemEmployee();
        systemEmployee.setUser(employer);
        systemEmployeeService.addEmployer(systemEmployee);

        return new SuccessResult("Employer registration completed successfully.");
    }
    private Result checkPasswordMatch(final String password, final String confirmPassword) {
        return password.equals(confirmPassword) ? new SuccessResult("Successful password") : new ErrorsResult("Passwords not match");
    }
    private Result isCorporateEmail(final String email, final String website) {
        return email.split("@")[1].equals(website) ? new SuccessResult("Successful Email") : new ErrorsResult("Email not corporate");
    }
}
