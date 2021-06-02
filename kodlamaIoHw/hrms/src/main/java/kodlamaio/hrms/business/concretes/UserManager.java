package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.*;
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
    public User getUser(int id) {
        return null;
    }

    @Override
    public DataResult<List<User>> getAllUser() {
        return new SuccessDataResult<List<User>>(this.userRepository.findAll(),"Listed data");
    }

    @Override
    public Result addUserAccount(User user) {

        this.userRepository.save(user);
        return new SuccessResult("Added user");
    }

    @Override
    public Result updateUserAccount(User user) {
        this.userRepository.save(user);
        return new SuccessResult("Updated User");
    }

    @Override
    public Result deleteUserAccount(User user) {
        this.userRepository.delete(user);
        return new SuccessResult("Deleted User");
    }

    @Override
    public DataResult<User> getUserByEmail(String email) {
        return new SuccessDataResult<User>(this.userRepository.findUserByEmail(email));
    }
}
