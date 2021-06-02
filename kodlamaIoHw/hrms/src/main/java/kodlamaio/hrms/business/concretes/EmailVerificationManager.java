package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationRepository;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationManager implements EmailVerificationService {

	private EmailVerificationRepository emailVerificationRepository;

	@Autowired
	public EmailVerificationManager(EmailVerificationRepository emailVerificationRepository) {
		super();
		this.emailVerificationRepository = emailVerificationRepository;
	}

	@Override
	public Result add(EmailVerification emailVerification) {
		this.emailVerificationRepository.save(emailVerification);
		return null;
	}

}
