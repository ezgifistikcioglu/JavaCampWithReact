package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserRepository;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public Result addUserAccount(User user) {
        return null;
    }

    @Override
    public boolean addVerificationEmail(User user) {
        return false;
    }

    @Override
    public boolean signUp(String email, String password) {
        return false;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public DataResult<List<User>> getAllUser() {
        return new SuccessDataResult<List<User>>(this.userRepository.findAll(),"Listed data");
    }

    @Override
    public Result add(User User) {
        this.userRepository.save(User);
        return new SuccessResult("Added User");
    }

    @Override
    public DataResult<User> getUserByEmail(String email) {
        return new SuccessDataResult<User>(this.userRepository.findUserByEmail(email));
    }
}
