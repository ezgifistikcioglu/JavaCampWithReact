package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.*;
import kodlamaio.hrms.core.adapters.concretes.MernisServiceAdapter;
import kodlamaio.hrms.core.utilities.results.ErrorsResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginManager implements LoginService {

    private UserService userService;
    private JobSeekerService jobSeekerService;
    private EmployerService employerService;
    private MernisServiceAdapter mernisServiceAdapter;
    private EmailVerificationService emailVerificationService;

    @Autowired
    public LoginManager(UserService userService, JobSeekerService jobSeekerService, EmployerService employerService, EmailVerificationService emailVerificationService) {
        super();
        this.userService = userService;
        this.jobSeekerService = jobSeekerService;
        this.employerService = employerService;
        this.emailVerificationService = emailVerificationService;
    }


    @Override
    public Result loginEmployer(Employer employer, String confirmPassword) {
        if (!checkInfoForEmployer(employer)) {
            return new ErrorsResult("Entered information is missing.");
        }
        if (!confirmPassword(employer.getPassword(), confirmPassword)) {
            return new ErrorsResult("Passwords do not match.");
        }
        if (!checkEmail(employer.getEmail())) {
            return new ErrorsResult(employer.getEmail() + " is already exist.");
        }
        employerService.addEmployer(employer);
        return new SuccessResult("Registration has been successfully completed");
    }

    @Override
    public Result loginJobSeeker(JobSeeker jobSeeker, String confirmPassword) {
        if (!checkInfoForJobseeker(jobSeeker)) {
            return new ErrorsResult("Entered information is missing.");
        }
        if (!checkIdentityNumber(jobSeeker.getTcNo())) {
            return new ErrorsResult(jobSeeker.getTcNo() + " is already exist.");
        }
        if (mernisServiceAdapter.checkIfRealPerson(jobSeeker)) {
            return new ErrorsResult("User is not verified.");
        }
        if (!checkEmail(jobSeeker.getEmail())) {
            return new ErrorsResult(jobSeeker.getEmail() + " is already exist.");
        }
        jobSeekerService.add(jobSeeker);
        return new SuccessResult("Registration has been successfully completed");
    }


    private boolean checkInfoForEmployer(Employer employer) {

        if (employer.getCompanyName() != null && employer.getWebAddress() != null && employer.getTelephoneNumber() != null && employer.getEmail() != null && employer.getPassword() != null) {
            return true;
        }
        return false;
    }

    private boolean checkEmailAndDomain(String email, String domain) {

        if (email != null && domain != null) {
            return true;
        }
        return false;
    }

    private boolean checkInfoForJobseeker(JobSeeker jobSeeker) {

        if (jobSeeker.getFirstname() != null && jobSeeker.getLastname() != null && jobSeeker.getTcNo() != null
                && jobSeeker.getBirthYear() != null && jobSeeker.getEmail() != null
                && jobSeeker.getPassword() != null) {
            return true;
        }
        return false;
    }

    private boolean checkIdentityNumber(String tcNo) {

        if (tcNo.length() > 11) {
            if (this.jobSeekerService.getJobSeekerByTcNo(tcNo).getData() == null) {
                return true;
            }
        }
        return false;
    }


    private boolean checkEmail(String email) {

        if (this.userService.getUserByEmail(email).getData() == null) {
            return true;
        }
        return false;
    }

    private boolean confirmPassword(String password, String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return false;
        }
        return true;
    }

    private void activationEmailRecord(int id, String code, String email) {
        EmailVerification emailVerification = new EmailVerification(id, code, false);
        this.emailVerificationService.add(emailVerification);
        System.out.println("Activation code has been sended to : " + email);
    }
}
