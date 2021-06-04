package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.dtos.LoginForEmailVerificationDto;

public interface EmailVerificationService {

	Result add(EmailVerification emailVerification);
	Result update(EmailVerification emailVerification);
	Result delete(EmailVerification emailVerification);
	Result sendActivationCode(User user, String... emails);

	Result verify(LoginForEmailVerificationDto emailVerificationDto);
}
