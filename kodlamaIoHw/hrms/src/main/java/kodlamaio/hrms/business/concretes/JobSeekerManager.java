package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapters.abstracts.MernisCheckService;
import kodlamaio.hrms.core.utilities.business.Rules;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerRepository;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.LoginForJobSeekerDto;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class JobSeekerManager implements JobSeekerService {

    private JobSeekerRepository jobSeekerRepository;
    private UserService userService;
    private EmailVerificationService emailVerificationService;
    private MernisCheckService mernisCheckService;

    @Autowired
    public JobSeekerManager(JobSeekerRepository jobSeekerRepository, UserService userService, EmailVerificationService emailVerificationService, MernisCheckService mernisCheckService) {
        super();
        this.jobSeekerRepository = jobSeekerRepository;
        this.userService = userService;
        this.emailVerificationService = emailVerificationService;
        this.mernisCheckService = mernisCheckService;
    }


    @Override
    public DataResult<JobSeeker> getJobSeekerByTcNo(String tcNo) {
        return null;
    }

    @Override
    public DataResult<JobSeeker> getJobSeekerByFirstname(String firstName) {
        return null;
    }

    @Override
    public DataResult<JobSeeker> getJobSeekerByLastname(String lastName) {
        return null;
    }

    @Override
    public DataResult<JobSeeker> getJobSeekerByBirthYear(Date birthYear) {
        return null;
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerRepository.findAll(), "Listed data");
    }

    @Override
    public Result add(JobSeeker JobSeeker) {
        this.jobSeekerRepository.save(JobSeeker);
        return new SuccessResult("Added JobSeeker");
    }

    @Override
    public Result register(LoginForJobSeekerDto jobSeekerDto) {

        var res = Rules.run(
                userService.checkByEmail(jobSeekerDto.getEmail()), checkPasswordMatch(jobSeekerDto.getPassword(), jobSeekerDto.getConfirmPassword()),
                isThereTCNo(jobSeekerDto.getEmail()));

        if (!Objects.requireNonNull(res).isSuccess()) {
            return res;
        }
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setEmail(jobSeekerDto.getEmail());
        jobSeeker.setTcNo(jobSeekerDto.getTcNo());
        jobSeeker.setBirthYear(jobSeekerDto.getBirthYear());
        jobSeeker.setFirstname(jobSeeker.getFirstname());
        jobSeeker.setLastname(jobSeeker.getLastname());
        jobSeeker.setPassword(jobSeekerDto.getPassword());
        jobSeeker.setConfirmPassword(jobSeeker.getConfirmPassword());

        add(jobSeeker);

        emailVerificationService.sendActivationCode(jobSeeker, jobSeeker.getEmail());
        mernisCheckService.checkIfRealPerson(jobSeekerDto);

        return new SuccessResult("Job Seeker registration completed successfully.");
    }

    @Override
    public Result isThereTCNo(String tcNo) {
        var res = jobSeekerRepository.findJobSeekerByTcNo(tcNo);
        return res == null ? new SuccessResult("True TC")
                : new ErrorsResult("There is already a job seeker with this TC number.");
    }

    private Result checkPasswordMatch(final String password, final String confirmPassword) {
        return password.equals(confirmPassword) ? new SuccessResult("Successful password") : new ErrorsResult("Passwords not match");
    }
}
