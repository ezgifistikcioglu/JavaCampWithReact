package kodlamaio.hrms.core.adapters.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface ValidateCheckEmailService {

    Result sendValidationEmail(String cc, String emailSubject, String emailContent);
}
