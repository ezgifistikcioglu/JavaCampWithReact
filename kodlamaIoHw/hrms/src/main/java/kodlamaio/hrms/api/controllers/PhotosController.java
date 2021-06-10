package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.PhotoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class PhotosController {

	private PhotoService photoService;
	private JobSeekerService jobSeekerService;

	@Autowired
	public PhotosController(PhotoService photoService, JobSeekerService jobSeekerService) {
		super();
		this.photoService = photoService;
		this.jobSeekerService = jobSeekerService;
	}

	
	@PostMapping(value = "/add")
	public Result add(@RequestParam(value = "id") int id, @RequestParam(value = "imageFile") MultipartFile imageFile){
		JobSeeker jobSeeker = this.jobSeekerService.getById(id).getData();
		PhotoInfo photoInfo = new PhotoInfo();
		photoInfo.setJobSeeker(jobSeeker);
		return this.photoService.add(photoInfo, imageFile);
	}
	
	@PostMapping("/update")
	public Result update( @RequestBody PhotoInfo photoInfo){
		return this.photoService.update(photoInfo);
		
	}
	
	@PostMapping("/delete")
	public Result delete( @RequestParam int  id){
		return this.photoService.delete(id);
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<PhotoInfo>> getAll(){
		return this.photoService.getAll();
	}
	
	@GetMapping("/getAllByCandidateId")
	public DataResult<PhotoInfo> getByCandidateId(@RequestParam int id){
		return this.photoService.getByJobSeekerId(id);
	}
	
	@GetMapping("/getById")
	public DataResult<PhotoInfo> getById(@RequestParam int id){
		return this.photoService.getById(id);
	}
}
