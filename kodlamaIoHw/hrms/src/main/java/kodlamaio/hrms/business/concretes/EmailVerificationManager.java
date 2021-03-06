package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.adapters.abstracts.ValidateCheckEmailService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationRepository;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.dtos.LoginForEmailVerificationDto;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailVerificationManager implements EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final ValidateCheckEmailService validateCheckEmailService;

    @Autowired
    public EmailVerificationManager(EmailVerificationRepository emailVerificationRepository, ValidateCheckEmailService validateCheckEmailService) {
        super();
        this.emailVerificationRepository = emailVerificationRepository;
        this.validateCheckEmailService = validateCheckEmailService;
    }

    @Override
    public Result addEmailVerification(EmailVerification emailVerification) {
        var checkActivationCode = isExistingActivationCode(emailVerification.getUser(), emailVerification.getEmail(), emailVerification.getAuthentication());

        if (!checkActivationCode) {
            return new ErrorsResult("This activation code already exists in the system");
        } else {
            this.emailVerificationRepository.save(emailVerification);
            return new SuccessDataResult<>(emailVerification, "Activation code sent successfully");
        }
    }

    @Override
    public Result updateEmailVerification(EmailVerification emailVerification) {
        Optional<EmailVerification> emailVerificationOptional = this.emailVerificationRepository.findById(emailVerification.getEmailId());

        if (!emailVerificationOptional.isPresent()) {
            return new ErrorsResult("This email ( id " + emailVerification.getEmailId() + "-" + emailVerification.getEmail() + " ) doesnt available!");
        } else {
            emailVerificationOptional.get().setEmail(emailVerification.getEmail());
            emailVerificationOptional.get().setAuthentication(emailVerification.getAuthentication());
            emailVerificationOptional.get().setUser(emailVerification.getUser());

            this.emailVerificationRepository.save(emailVerificationOptional.get());
            return new SuccessResult("EmailVerification (" + emailVerification.getEmailId() + ") updated successfully.");
        }
    }

    @Override
    public Result deleteEmailVerification(int id) {
        Optional<EmailVerification> emailVerificationOptional = this.emailVerificationRepository.findById(id);

        if (!emailVerificationOptional.isPresent()) {
            return new ErrorDataResult<>("User not found");
        } else {
            this.emailVerificationRepository.delete(emailVerificationOptional.get());
            return new SuccessResult("Deleted Email with id :" + id);
        }
    }

    @Override
    public Result sendActivationCode(User user, String... emails) {
        String verCode = "";
        for (final String email : emails) {
            final EmailVerification emailVerification = new EmailVerification();
            emailVerification.setUser(user);
            emailVerification.setEmail(email);
            verCode = "test"; // TODO check this verification code structure
            emailVerification.setAuthentication(verCode);

            emailVerificationRepository.save(emailVerification);

            validateCheckEmailService.sendValidationEmail(email,
                    "emailSubject",
                    String.format("%swww.localhost:8080/api/emailactivations/verify?activationCode=%s&email=%s",
                            "emailContent",
                            emailVerification.getAuthentication(),
                            email));
        }
        return new SuccessResult("Activation code sent successfully : verificationCode " + verCode);
    }

    @Override
    public Result verify(LoginForEmailVerificationDto emailVerificationDto) {
        Optional<EmailVerification> emailVerification = this.emailVerificationRepository.findByEmailAndAuthentication(emailVerificationDto.getEmail(), emailVerificationDto.getActivationCode());

        if (!emailVerification.isPresent()) {
            return new ErrorsResult("Failed to verify email.");
        } else {
            emailVerification.get().setApproved(true);
            this.emailVerificationRepository.save(emailVerification.get());
            return new SuccessResult("Email has been successfully verified.");
        }
    }

    private boolean isExistingActivationCode(User user, String email, String activationCode) {
        return sendActivationCode(user, email, activationCode).isSuccess();
    }
}
