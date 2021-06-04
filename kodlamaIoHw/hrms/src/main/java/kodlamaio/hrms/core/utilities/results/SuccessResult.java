package kodlamaio.hrms.core.utilities.results;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public class SuccessResult extends Result{
    public SuccessResult(DataResult<JobSeeker> jobSeekerByTcNo){
        super(true);
    }
    public SuccessResult(String message){
        super(true, message);
    }
}
