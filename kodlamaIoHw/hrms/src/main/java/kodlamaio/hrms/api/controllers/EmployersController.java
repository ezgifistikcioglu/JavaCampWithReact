package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.LoginForEmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employers")
@CrossOrigin
public class EmployersController {
    @Autowired
    private EmployerService employerService;

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll() {
        return this.employerService.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity<Result> register(@Valid @RequestBody final LoginForEmployerDto loginForEmployerDto) {
        final Result result = employerService.register(loginForEmployerDto);

        if (!result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteEmployer/{id}")
    public ResponseEntity<Result> deleteEmployer(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.employerService.deleteEmployer(id));
    }

    @PostMapping("/confirmEmployer")
    public ResponseEntity<Result> confirmEmployer(@Valid @RequestBody LoginForEmployerDto employerDto) {
        final Result result = employerService.confirmEmployer(employerDto);

        if (!result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllActiveAndApprovedEmployerList")
    public DataResult<List<Employer>> getAllActiveAndApprovedEmployerList() {
        return this.employerService.getAllActiveAndApprovedEmployerList();
    }
    @GetMapping("/getAllWaitApproveEmployerList")
    public DataResult<List<Employer>> getAllWaitApproveEmployerList() {
        return this.employerService.getAllWaitApproveEmployerList();
    }
}
