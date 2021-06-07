package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.business.Rules;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.UserRepository;
import kodlamaio.hrms.entities.concretes.Advertisement;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import kodlamaio.hrms.entities.concretes.User;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public Result checkByEmail(String email) {
        return this.userRepository.findUserByEmail(email) == null ? new SuccessResult("user email available for add") : new ErrorsResult("The user with this email already exists.");
    }

    @Override
    public DataResult<User> getUser(int id) {
        Optional<User> user = this.userRepository.findById(id);

        if (!user.isPresent()) {
            return new ErrorDataResult<>("User not found");
        } else {
            return new SuccessDataResult<>(user.get());
        }
    }

    @Override
    public DataResult<List<User>> getAllUser() {
        return new SuccessDataResult<>(this.userRepository.findAll(), "Listed data");
    }

    @Override
    public Result addUserAccount(User user) {
        if (!isEmailValid(user.getEmail())) {
            return new ErrorsResult("Error : Email not valid! " + user.getEmail());
        } else {
            if (this.userRepository.findUserByEmail(user.getEmail()) != null) {
                return new ErrorsResult("Error : User email : " + user.getEmail() + " already exists!");
            } else {
                if (!checkPasswordMatch(user.getPassword(), user.getConfirmPassword())) {
                    return new ErrorsResult("Error : Check User password matching : " + user.getPassword() +"-"+user.getConfirmPassword());
                } else {
                    // TODO : check user verification
                    // emailVerificationService.sendActivationCode(employer, employer.getEmail());
                    this.userRepository.save(user);
                    return new SuccessResult("Added user successfully");
                }
            }
        }
    }

    @Override
    public Result updateUserAccount(User user) {
        Optional<User> userOpt = this.userRepository.findById(user.getId());

        if (!userOpt.isPresent()) {
            return new ErrorsResult("This user ( id " + user.getId() + "-" + user.getEmail() + " ) doesnt available!");
        } else {
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                return new ErrorsResult("User password and confirmPassword values must be same!");
            } else {

                userOpt.get().setEmail(user.getEmail());
                userOpt.get().setPassword(user.getPassword());
                userOpt.get().setConfirmPassword(user.getConfirmPassword());
                userOpt.get().setDeletedUser(user.isDeletedUser());

                this.userRepository.save(userOpt.get());
                return new SuccessResult("User (" + user.getId() + ") updated successfully.");
            }
        }
    }

    @Override
    public Result deleteUserAccount(int id) {
        Optional<User> user = this.userRepository.findById(id);

        if (!user.isPresent()) {
            return new ErrorDataResult<>("User not found");
        } else {
            this.userRepository.deleteById(id);
            return new SuccessResult("Deleted User wit id :" + id);
        }
    }

    @Override
    public DataResult<User> findUserByEmail(String email) {
        final User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return new ErrorDataResult<>("User not found");
        } else {
            return new SuccessDataResult<>(user.getEmail());
        }
    }

    private boolean checkPasswordMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
}
