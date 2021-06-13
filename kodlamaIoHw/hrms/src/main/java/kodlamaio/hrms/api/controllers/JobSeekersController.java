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
@CrossOrigin
public class JobSeekersController {
    private final JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekersController(JobSeekerService jobSeekerService) {
        super();
        this.jobSeekerService = jobSeekerService;
    }

    @GetMapping("/getall")
    public DataResult<List<JobSeeker>> getAll() {
        return this.jobSeekerService.getAll();
    }

    @PostMapping("/addJobSeeker")
    public ResponseEntity<Result> addJobSeeker(@RequestBody JobSeeker jobSeeker) {
        return ResponseEntity.ok(this.jobSeekerService.addJobSeeker(jobSeeker));
    }

    @GetMapping("/getById")
    public DataResult<JobSeeker> getById(@RequestParam int id) {
        return this.jobSeekerService.getById(id);
    }

    @PostMapping("/updateJobSeeker")
    public ResponseEntity<Result> updateJobSeeker(@RequestBody JobSeeker jobSeeker) {
        return ResponseEntity.ok(this.jobSeekerService.updateJobSeeker(jobSeeker));
    }

    @PostMapping("/deleteJobSeeker")
    public ResponseEntity<Result> deleteJobSeeker(@RequestBody int id) {
        return ResponseEntity.ok(this.jobSeekerService.deleteJobSeeker(id));
    }

    @PostMapping("/register")
    public ResponseEntity<Result> register(@RequestBody LoginForJobSeekerDto jobSeekerForRegisterDto) {
        final Result result = jobSeekerService.register(jobSeekerForRegisterDto);

        if (!result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(result);
    }
}
