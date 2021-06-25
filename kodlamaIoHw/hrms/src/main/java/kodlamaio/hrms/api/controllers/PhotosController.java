package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.CVService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.PhotoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class PhotosController {

    private final PhotoService photoService;
    private final JobSeekerService jobSeekerService;
    private final CVService cvService;

    @Autowired
    public PhotosController(PhotoService photoService, JobSeekerService jobSeekerService, CVService cvService) {
        super();
        this.photoService = photoService;
        this.jobSeekerService = jobSeekerService;
        this.cvService = cvService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<Result> add(@RequestParam("id") int id, @RequestParam("cvId") int cvId,@RequestParam MultipartFile imageFile) {
        JobSeeker jobSeeker = this.jobSeekerService.getById(id).getData();
        Cv cv = this.cvService.getByCvId(cvId).getData();
        PhotoInfo photoInfo = new PhotoInfo();
        photoInfo.setJobSeeker(jobSeeker);
        photoInfo.setCv(cv);
        Result result = photoService.uploadPhoto(photoInfo, imageFile);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody PhotoInfo photoInfo) {
        return this.photoService.update(photoInfo);

    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") int id) {
        return this.photoService.delete(id);

    }

    @GetMapping("/getAll")
    public DataResult<List<PhotoInfo>> getAll() {
        return this.photoService.getAll();
    }


    @GetMapping("/getById/{id}")
    public DataResult<PhotoInfo> getById(@PathVariable("id") int id) {
        return this.photoService.getById(id);
    }
}
