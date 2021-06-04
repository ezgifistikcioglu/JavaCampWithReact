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

import java.time.LocalDateTime;
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
	public Result add(EmailVerification emailVerification) {
		var checkActivationCode = isExistingActivationCode(emailVerification.getUser(),emailVerification.getEmail(), emailVerification.getAuthentication());

		if (checkActivationCode)
			return new ErrorsResult("This activation code already exists in the system");
		this.emailVerificationRepository.save(emailVerification);
		return new SuccessDataResult<>(emailVerification, "Activation code sent successfully");
	}

	@Override
	public Result update(EmailVerification emailVerification) {
		this.emailVerificationRepository.save(emailVerification);
		return new SuccessDataResult<>(emailVerification, "Activation code update successfully");
	}

	@Override
	public Result delete(EmailVerification emailVerification) {
		this.emailVerificationRepository.delete(emailVerification);
		return new SuccessDataResult<>(emailVerification, "Activation code delete successfully");
	}

	@Override
	public Result sendActivationCode(User user, String... emails) {
		for (final String email : emails) {
			final EmailVerification emailVerification = new EmailVerification();
			emailVerification.setUser(user);
			emailVerification.setEmail(email);
			emailVerification.setAuthentication("test");
			emailVerification.setActivationDate(LocalDateTime.now().plusMonths(1));

			emailVerificationRepository.save(emailVerification);

			validateCheckEmailService.sendValidationEmail(email,
					"emailSubject",
					String.format("%swww.localhost:8080/api/emailactivations/verify?activationCode=%s&email=%s",
							"emailContent",
							emailVerification.getAuthentication(),
							email));
		}
		return new SuccessResult("Activation code sent successfully");
	}

	@Override
	public Result verify(LoginForEmailVerificationDto emailVerificationDto) {
		final Optional<EmailVerification> emailVerification = this.emailVerificationRepository.findByEmailAndAuthentication(emailVerificationDto.getEmail(), emailVerificationDto.getActivationCode());
		if (emailVerification.equals(Optional.empty())) {
			return new ErrorsResult("Failed to verify email.");
		}
		emailVerification.get().setApproved(true);
		this.emailVerificationRepository.save(emailVerification.get());
		return new SuccessResult("Email has been successfully verified.");
	}

	private boolean isExistingActivationCode(User user,String email, String activationCode) {
		return sendActivationCode(user,email,activationCode).isSuccess();
	}
}
