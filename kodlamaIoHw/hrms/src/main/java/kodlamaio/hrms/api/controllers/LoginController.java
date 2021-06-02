package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.LoginService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
	@PostMapping("/loginEmployer")
	public Result loginEmployer(@RequestBody Employer employer, String confirmPassword)
	{
		return loginService.loginEmployer(employer, confirmPassword);
	}
	
	@PostMapping("/loginJobSeeker")
	public Result loginJobSeeker(@RequestBody JobSeeker jobseeker, String confirmPassword)
	{
		return loginService.loginJobSeeker(jobseeker, confirmPassword);
	}
}
