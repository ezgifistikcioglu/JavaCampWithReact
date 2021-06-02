package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.User;

import java.util.List;

public interface UserService {
    User getUser(int id);
    DataResult<List<User>> getAllUser();
    Result addUserAccount(User user);
    Result updateUserAccount(User user);
    Result deleteUserAccount(User user);
    DataResult<User> getUserByEmail(String email);
}
