package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.CVService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.dtos.CvDetailForJobSeekerDto;
import kodlamaio.hrms.entities.dtos.CvDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cvs")
@CrossOrigin
public class CVsController {
    @Autowired
    private CVService cvService;

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Cv>>> getAll() {
        DataResult<List<Cv>> result = cvService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody CvDto cvDto) {
        return ResponseEntity.ok(this.cvService.add(cvDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.cvService.delete(id));
    }

     @PostMapping("/update")
     public ResponseEntity<Result> update(@RequestBody CvDto cvDto) {
         Result result = cvService.update(cvDto);
         if (!result.isSuccess()) {
             return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
         } else {
             return ResponseEntity.ok(result);
         }
     }

    @GetMapping("/getByCvId/{id}")
    public ResponseEntity<DataResult<Cv>> getByCvId(@PathVariable("id") int id) {
        DataResult<Cv> result = cvService.getByCvId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findByJobSeekerUserId/{id}")
    public ResponseEntity<DataResult<Cv>> findByJobSeekerUserId(@PathVariable("id") int id) {
        DataResult<Cv> result = cvService.findByJobSeekerUserId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getCvDetailForJobSeekerById/{id}")
    public DataResult<CvDetailForJobSeekerDto> getCvDetailForJobSeekerById(@PathVariable("id") int id) {
        return this.cvService.getCvDetailForJobSeekerById(id);
    }
}
