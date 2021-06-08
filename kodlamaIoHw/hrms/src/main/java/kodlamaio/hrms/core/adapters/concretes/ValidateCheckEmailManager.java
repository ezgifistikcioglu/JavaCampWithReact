package kodlamaio.hrms.core.adapters.concretes;

import kodlamaio.hrms.core.adapters.abstracts.ValidateCheckEmailService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ValidateCheckEmailManager implements ValidateCheckEmailService {

    private final EmailVerificationRepository emailVerificationRepository;

    @Autowired
    public ValidateCheckEmailManager(EmailVerificationRepository emailVerificationRepository) {
        super();
        this.emailVerificationRepository = emailVerificationRepository;
    }
    
    @Override
    public Result sendValidationEmail(String cc, String emailSubject, String emailContent) {
        return null;
    }
}
