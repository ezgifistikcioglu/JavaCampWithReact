package kodlamaio.hrms.core.adapters.concretes;

import kodlamaio.hrms.core.adapters.abstracts.ValidateCheckEmailService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationRepository;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ValidateCheckEmailManager implements ValidateCheckEmailService {

    private EmailVerificationRepository emailVerificationRepository;

    @Autowired
    public ValidateCheckEmailManager(EmailVerificationRepository emailVerificationRepository) {
        super();
        this.emailVerificationRepository = emailVerificationRepository;
    }

    @Override
    public boolean addNewAccount(User user) {
        if (loginWithEmail(user.getEmail())){
            System.out.println("Authentication credentials succeeded.");
            return EmailWithRegex.isEmailValid(user.getEmail()) && EmailWithRegex.isPasswordValid(user.getPassword());
        }
        System.out.println("Authentication credentials fail.");
        return false;
    }

    @Override
    public void sendValidationEmail(User user, String content) {

    }

    @Override
    public void sendValidationLink(User user) {

    }

    @Override
    public boolean isClickedToValidationLink(User user) {
        return false;
    }

    @Override
    public Result add(EmailVerification emailVerification) {
        this.emailVerificationRepository.save(emailVerification);
        return new SuccessResult("The verification code is saved!");
    }

    public boolean loginWithEmail(String email){
        System.out.println("Signed in with Email Authentication. " + email);
        return true;
    }
}
