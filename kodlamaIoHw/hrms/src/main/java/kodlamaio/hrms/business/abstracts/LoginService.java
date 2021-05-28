package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Login;
import kodlamaio.hrms.entities.concretes.User;

public interface LoginService {
    boolean addNewAccount(User user);
    void sendValidationEmail(User user, String content);
    void sendValidationLink(User user);
    boolean isClickedToValidationLink(User user);

    Result add(Login login);
}
