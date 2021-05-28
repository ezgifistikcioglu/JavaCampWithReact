package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.LoginService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LoginRepository;
import kodlamaio.hrms.entities.concretes.Login;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginManager implements LoginService {

    private LoginRepository loginRepository;

    @Autowired
    public LoginManager(LoginRepository loginRepository) {
        super();
        this.loginRepository = loginRepository;
    }


    @Override
    public boolean addNewAccount(User user) {
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
    public Result add(Login login) {
        this.loginRepository.save(login);
        return new SuccessResult("The verification code is saved!");
    }
}
