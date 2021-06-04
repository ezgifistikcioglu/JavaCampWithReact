package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.User;

import java.util.List;

public interface UserService {
    Result checkByEmail(String email);

    User getUser(int id);

    Result addUserAccount(User user);
    Result updateUserAccount(User user);
    Result deleteUserAccount(User user);
    Result confirmActivation(String email, String activationCode);

    DataResult<List<User>> getAllUser();
    DataResult<User> getUserByEmail(String email);
}
