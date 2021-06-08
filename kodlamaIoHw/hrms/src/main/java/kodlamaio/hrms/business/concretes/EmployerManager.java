package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmployerRepository;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import kodlamaio.hrms.entities.dtos.LoginForEmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerRepository employerRepository;
    private EmailVerificationService emailVerificationService;
    private SystemEmployeeService systemEmployeeService;

    @Autowired
    public EmployerManager(EmployerRepository employerRepository, EmailVerificationService emailVerificationService, SystemEmployeeService systemEmployeeService) {
        super();
        this.employerRepository = employerRepository;
        this.emailVerificationService = emailVerificationService;
        this.systemEmployeeService = systemEmployeeService;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerRepository.findAll(), "Listed data");
    }

    @Override
    public Result addEmployer(Employer employer) {
        this.employerRepository.save(employer);
        return new SuccessResult("Added employer");
    }

    @Override
    public Result updateEmployer(Employer employer) {

        Optional<Employer> employerOptional = this.employerRepository.findById(employer.getId());

        if (!employerOptional.isPresent()) {
            return new ErrorsResult("This employer ( id " + employer.getId() + "-" + employer.getEmail() + " ) doesnt available!");
        } else {

            employerOptional.get().setEmail(employer.getEmail());
            employerOptional.get().setPassword(employer.getPassword());
            employerOptional.get().setConfirmPassword(employer.getConfirmPassword());
            employerOptional.get().setDeletedUser(employer.isDeletedUser());
            employerOptional.get().setCompanyName(employer.getCompanyName());
            employerOptional.get().setWebAddress(employer.getWebAddress());
            employerOptional.get().setAdvertisementList(employer.getAdvertisementList());
            employerOptional.get().setTelephoneNumber(employer.getTelephoneNumber());

            this.employerRepository.save(employerOptional.get());
            return new SuccessResult("Employer (" + employer.getId() + ") updated successfully.");
        }
    }

    @Override
    public Result deleteEmployer(int id) {
        Optional<Employer> employerOptional = this.employerRepository.findById(id);

        if (!employerOptional.isPresent()) {
            return new ErrorDataResult<>("JobSeeker not found");
        } else {
            this.employerRepository.deleteById(id);
            return new SuccessResult("Deleted employer with id :" + id);
        }
    }

    @Override
    public Result isEmailAvailable(String email) {
        return employerRepository.findByEmail(email).equals(Optional.empty()) ? new SuccessResult("Successful email for employer") : new ErrorsResult("An employer with this email already exists");
    }

    @Override
    public Result register(LoginForEmployerDto employerDto) {
        if (!isCorporateEmail(employerDto.getEmail(), employerDto.getWebAddress())) {
            return new ErrorsResult("Error : Email not valid! " + employerDto.getEmail());
        } else {
            if (this.employerRepository.findByEmail(employerDto.getEmail()).isPresent()) {
                return new ErrorsResult("Error : This email : " + employerDto.getEmail() + " already exists!");
            } else {
                if (!checkPasswordMatch(employerDto.getPassword(), employerDto.getConfirmPassword()) && !isCorporateEmail(employerDto.getEmail(), employerDto.getWebAddress())) {
                    return new ErrorsResult("Error : Please Check Employer password , email and website : " + employerDto);
                } else {
                    Employer employer = new Employer();
                    employer.setEmail(employerDto.getEmail());
                    employer.setCompanyName(employerDto.getCompanyName());
                    employer.setWebAddress(employerDto.getWebAddress());
                    employer.setTelephoneNumber(employerDto.getTelephoneNumber());
                    employer.setPassword(employerDto.getPassword());
                    employer.setConfirmPassword(employerDto.getConfirmPassword());
                    addEmployer(employer);
                    // TODO : check also save user repository too

                    emailVerificationService.sendActivationCode(employer, employer.getEmail());
                    SystemEmployee systemEmployee = new SystemEmployee();
                    systemEmployee.setUser(employer);
                    // TODO : check here, Does this check add Employer
                    systemEmployeeService.addEmployer(systemEmployee);

                    return new SuccessResult("Employer registration completed successfully.");
                }
            }
        }
    }

    private boolean checkPasswordMatch(String password, String confirmPassword) {
        boolean resultValidate = password.length() >= 4;
        if (!resultValidate) {
            System.out.println("Password must not be less than 4 characters, please check your password: " + password);
            return false;
        }
        return password.equals(confirmPassword);
    }

    private boolean isCorporateEmail(final String email, final String website) {
        return email.split("@")[1].equals(website);
    }
}
