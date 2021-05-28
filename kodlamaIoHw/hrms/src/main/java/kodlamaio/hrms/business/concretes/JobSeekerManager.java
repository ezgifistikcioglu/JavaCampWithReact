package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerRepository;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerManager implements JobSeekerService {

    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    public JobSeekerManager(JobSeekerRepository jobSeekerRepository) {
        super();
        this.jobSeekerRepository = jobSeekerRepository;
    }


    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerRepository.findAll(),"Listed data");
    }

    @Override
    public Result add(JobSeeker JobSeeker) {
        this.jobSeekerRepository.save(JobSeeker);
        return new SuccessResult("Added JobSeeker");
    }
}
