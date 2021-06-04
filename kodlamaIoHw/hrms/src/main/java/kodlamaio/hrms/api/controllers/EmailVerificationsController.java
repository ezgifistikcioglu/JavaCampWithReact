package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.LoginForEmailVerificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/email-verifications")
public class EmailVerificationsController {
	private final EmailVerificationService emailVerificationService;

	@Autowired
	public EmailVerificationsController(final EmailVerificationService emailVerificationService) {
		this.emailVerificationService = emailVerificationService;
	}

	@GetMapping("/verify")
	public ResponseEntity<Result> verify(@Valid final LoginForEmailVerificationDto loginForEmailVerificationDto) {
		final Result result = emailVerificationService.verify(loginForEmailVerificationDto);

		if (!result.isSuccess())
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

		return ResponseEntity.ok(result);
	}
}
