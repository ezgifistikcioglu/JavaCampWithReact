package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.dtos.LoginForEmailVerificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/email-verifications")
public class EmailVerificationsController {
    private final EmailVerificationService emailVerificationService;

    @Autowired
    public EmailVerificationsController(final EmailVerificationService emailVerificationService) {
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping("/addEmailVerification")
    public ResponseEntity<Result> addEmailVerification(@RequestBody EmailVerification emailVerification) {
        final Result result = emailVerificationService.addEmailVerification(emailVerification);
        if (!result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateEmailVerification")
    public ResponseEntity<Result> updateEmailVerification(@RequestBody EmailVerification emailVerification) {
        return ResponseEntity.ok(this.emailVerificationService.updateEmailVerification(emailVerification));
    }

    @PostMapping("/deleteEmailVerification")
    public ResponseEntity<Result> deleteEmailVerification(@RequestBody int id) {
        return ResponseEntity.ok(this.emailVerificationService.deleteEmailVerification(id));
    }

    @GetMapping("/verify")
    public ResponseEntity<Result> verify(@Valid final LoginForEmailVerificationDto loginForEmailVerificationDto) {
        final Result result = emailVerificationService.verify(loginForEmailVerificationDto);

        if (!result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(result);
    }
}
