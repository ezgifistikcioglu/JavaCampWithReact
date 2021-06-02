package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;

public interface EmailVerificationService {

	Result add(EmailVerification emailVerification);
}
