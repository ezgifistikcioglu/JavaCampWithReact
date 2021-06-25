package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.UserRepository;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public Result checkUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email) != null ? new SuccessResult("user email available for uploadPhoto") : new ErrorsResult("The user with this email already exists.");
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
                    return new ErrorsResult("Error : Check User password matching : " + user.getPassword() + "-" + user.getConfirmPassword());
                } else {
                    // TODO : check user verification if it need that.
                    this.userRepository.save(user);
                    return new SuccessResult("Added user successfully");
                }
            }
        }
    }

    @Override
    public Result updateUserAccount(User user) {
        Optional<User> userOpt = this.userRepository.findById(user.getUserId());

        if (!userOpt.isPresent()) {
            return new ErrorsResult("This user ( id " + user.getUserId() + "-" + user.getEmail() + " ) doesnt available!");
        } else {
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                return new ErrorsResult("User password and confirmPassword values must be same!");
            } else {

                userOpt.get().setEmail(user.getEmail());
                userOpt.get().setPassword(user.getPassword());
                userOpt.get().setConfirmPassword(user.getConfirmPassword());

                this.userRepository.save(userOpt.get());
                return new SuccessResult("User (" + user.getUserId() + ") updated successfully.");
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
            return new SuccessResult("Deleted User with id :" + id);
        }
    }

    @Override
    public DataResult<User> findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            return new ErrorDataResult<>("User not found");
        } else {
            return new SuccessDataResult<>(user.getEmail());
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

    private boolean isEmailValid(String email) {
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        boolean resultValidate = matcher.matches();
        if (!resultValidate) {
            System.out.println("Email address type not correct, please check your email address type: " + email);
        }
        return resultValidate;
    }
}
