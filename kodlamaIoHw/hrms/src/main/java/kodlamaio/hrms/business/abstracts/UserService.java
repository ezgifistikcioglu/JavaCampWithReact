package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.User;

import java.util.List;

public interface UserService {
    Result addUserAccount(User user);
    boolean addVerificationEmail(User user);
    boolean signUp(String email, String password);

    User getUser(int id);
    DataResult<List<User>> getAllUser();
    Result add(User user);
    DataResult<User> getUserByEmail(String email);
}
