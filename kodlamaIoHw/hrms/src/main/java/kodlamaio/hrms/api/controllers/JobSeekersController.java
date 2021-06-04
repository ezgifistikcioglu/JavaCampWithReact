package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.LoginForJobSeekerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/job-seekers")
public class JobSeekersController {
    private JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekersController(JobSeekerService jobSeekerService) {
        super();
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<JobSeeker>>> getAll(){
        return ResponseEntity.ok(this.jobSeekerService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody JobSeeker jobSeeker){
        return ResponseEntity.ok(this.jobSeekerService.add(jobSeeker));
    }

    @PostMapping("/register")
    public ResponseEntity<Result> register(@Valid @RequestBody LoginForJobSeekerDto jobSeekerForRegisterDto) {
        final Result result = jobSeekerService.register(jobSeekerForRegisterDto);

        if (!result.isSuccess())
            return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(result);
    }
}
