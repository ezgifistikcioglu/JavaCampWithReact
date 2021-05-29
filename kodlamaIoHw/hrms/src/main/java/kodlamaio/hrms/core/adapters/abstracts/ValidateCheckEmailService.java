package kodlamaio.hrms.core.adapters.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.MernisVerification;
import kodlamaio.hrms.entities.concretes.User;

public interface ValidateCheckEmailService {
    boolean addNewAccount(User user);
    void sendValidationEmail(User user, String content);
    void sendValidationLink(User user);
    boolean isClickedToValidationLink(User user);

    Result add(EmailVerification emailVerification);
}
