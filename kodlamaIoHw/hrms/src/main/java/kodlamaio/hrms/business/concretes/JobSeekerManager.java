package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapters.abstracts.MernisCheckService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerRepository;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.LoginForJobSeekerDto;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;
    private final UserService userService;
    private final EmailVerificationService emailVerificationService;
    private final MernisCheckService mernisCheckService;
    private final PhotoService photoService;

    @Autowired
    public JobSeekerManager(JobSeekerRepository jobSeekerRepository, UserService userService, EmailVerificationService emailVerificationService, MernisCheckService mernisCheckService, PhotoService photoService) {
        super();
        this.jobSeekerRepository = jobSeekerRepository;
        this.userService = userService;
        this.emailVerificationService = emailVerificationService;
        this.mernisCheckService = mernisCheckService;
        this.photoService = photoService;
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
    public DataResult<JobSeeker> getById(int id) {
        return new SuccessDataResult<>(this.jobSeekerRepository.getByUserId(id));
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        List<JobSeeker> jobSeekers = this.jobSeekerRepository.findAll();
        return new SuccessDataResult<>(jobSeekers);
    }

    @Override
    public DataResult<List<JobSeeker>> findJobSeekersById(int id) {
        List<JobSeeker> jobSeekers = jobSeekerRepository.findJobSeekersByUserId(id);
        if (!jobSeekers.isEmpty()) {
            return new ErrorDataResult<>("This JobSeeker Not Found");
        } else {
            return new SuccessDataResult<>(jobSeekers);
        }
    }

    @Override
    public Result addJobSeeker(JobSeeker jobSeeker) {
        LoginForJobSeekerDto jobSeekerDto = new LoginForJobSeekerDto();
        Result checkRegister = register(jobSeekerDto);
        if (getById(jobSeeker.getUserId()).getData() != null || !checkRegister.isSuccess()) {
            return new ErrorsResult(jobSeeker.getUserId() + "Same job seeker cannot repeat" + checkRegister.getMessage());
        } else {
            this.jobSeekerRepository.save(jobSeeker);
            return new SuccessResult("Added new job seeker");
        }
    }

    @Override
    public Result updateJobSeeker(JobSeeker jobSeeker) {
        Optional<JobSeeker> jobSeekerOptional = this.jobSeekerRepository.findById(jobSeeker.getUserId());

        if (!jobSeekerOptional.isPresent()) {
            return new ErrorsResult("This job seeker ( id " + jobSeeker.getUserId() + "-" + jobSeeker.getEmail() + "-" + jobSeeker + " ) doesnt available!");
        } else {

            jobSeekerOptional.get().setEmail(jobSeeker.getEmail());
            jobSeekerOptional.get().setPassword(jobSeeker.getPassword());
            jobSeekerOptional.get().setConfirmPassword(jobSeeker.getConfirmPassword());
            jobSeekerOptional.get().setBirthYear(jobSeeker.getBirthYear());
            jobSeekerOptional.get().setFirstname(jobSeeker.getFirstname());
            jobSeekerOptional.get().setLastname(jobSeeker.getLastname());
            jobSeekerOptional.get().setTcNo(jobSeeker.getTcNo());

            this.jobSeekerRepository.save(jobSeekerOptional.get());
            return new SuccessResult("Job Seeker (" + jobSeeker.getUserId() + ") updated successfully.");
        }
    }


    @Override
    public Result deleteJobSeeker(int id) {
        Optional<JobSeeker> jobSeeker = this.jobSeekerRepository.findById(id);

        if (!jobSeeker.isPresent()) {
            return new ErrorDataResult<>("JobSeeker not found");
        } else {
            this.jobSeekerRepository.deleteById(id);
            return new SuccessResult("Deleted JobSeeker with id :" + id);
        }
    }

    @Override
    public Result register(LoginForJobSeekerDto jobSeekerDto) {

        if (mernisCheckService.checkIfRealPerson(jobSeekerDto)) {

            if (isExistTCNo(jobSeekerDto.getTcNo())) {
                return new ErrorsResult("Error : There is already a job seeker with this TC number : " + jobSeekerDto.getTcNo());
            } else {

                var checkEmail = userService.checkUserByEmail(jobSeekerDto.getEmail());
                if (checkEmail.isSuccess()) {
                    return new ErrorsResult("Error : JobSeeker User email : " + jobSeekerDto.getEmail() + " already exists!");
                } else {

                    if (!checkPasswordMatch(jobSeekerDto.getPassword(), jobSeekerDto.getConfirmPassword())) {
                        return new ErrorsResult("Error : JobSeeker password and confirmPassword does not match! : " + jobSeekerDto.getPassword() + "-" + jobSeekerDto.getConfirmPassword());
                    } else {

                        JobSeeker jobSeeker = new JobSeeker();
                        jobSeeker.setEmail(jobSeekerDto.getEmail());
                        jobSeeker.setTcNo(jobSeekerDto.getTcNo());
                        jobSeeker.setBirthYear(jobSeekerDto.getBirthYear());
                        jobSeeker.setFirstname(jobSeekerDto.getFirstname());
                        jobSeeker.setLastname(jobSeekerDto.getLastname());
                        jobSeeker.setPassword(jobSeekerDto.getPassword());
                        jobSeeker.setConfirmPassword(jobSeekerDto.getConfirmPassword());
                        addJobSeeker(jobSeeker);

                        emailVerificationService.sendActivationCode(jobSeeker, jobSeeker.getEmail());

                        return new SuccessResult("Job Seeker registration completed successfully.");
                    }
                }
            }
        } else {
            return new ErrorsResult("Error : JobSeeker TC no NOT valid! : " + jobSeekerDto.getTcNo());
        }
    }

    @Override
    public boolean isExistTCNo(String tcNo) {
        var res = jobSeekerRepository.findJobSeekerByTcNo(tcNo);
        if (res == null) {
            System.out.println("There is Not available job seeker with this TC number : " + tcNo);
            return false;
        } else {
            System.out.println("There is already a job seeker with this TC number : " + tcNo);
            return true;
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


    public static boolean isFirstAndLastNameValid(String name) {
        String nameAndSurnameRegex = "^[a-zA-Z0-9_.]{2,20}$";
        Pattern pattern3 = Pattern.compile(nameAndSurnameRegex);
        Matcher matcher = pattern3.matcher(name);

        boolean resultValidate = matcher.matches();

        if (!resultValidate) {
            System.out.println("Name and surname must be at least 2 chars, please check your name length: " + name);
        }
        return resultValidate;
    }
}
