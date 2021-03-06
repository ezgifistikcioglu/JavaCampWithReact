package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.EducationInformationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Advertisement;
import kodlamaio.hrms.entities.concretes.EducationInformationForCv;
import kodlamaio.hrms.entities.dtos.EducationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educations")
@CrossOrigin
public class EducationsController {
    @Autowired
    private EducationInformationService educationInformationService;

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<EducationInformationForCv>>> getAll() {
        DataResult<List<EducationInformationForCv>> result = educationInformationService.getAll();
        return ResponseEntity.ok(result);
    }

   @PostMapping("/add")
   public ResponseEntity<Result> add(@RequestBody EducationDto educationDto) {
       return ResponseEntity.ok(this.educationInformationService.add(educationDto));
   }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.educationInformationService.delete(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@RequestBody EducationDto educationDto) {
        Result result = educationInformationService.update(educationDto);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result);
        }
    }
    @GetMapping("/getByEducationId/{id}")
    public DataResult<EducationInformationForCv> getByEducationId(@PathVariable("id") int id) {
        return this.educationInformationService.getByEducationId(id);
    }

    @GetMapping("/getByCvId/{id}")
    public DataResult<List<EducationInformationForCv>> getByCvId(@PathVariable("id") int id) {
        return this.educationInformationService.getByCv_CvId(id);
    }

    @GetMapping("/findByEducationId/{id}")
    public DataResult<List<EducationInformationForCv>> findByEducationId(@PathVariable("id") int id) {
        return this.educationInformationService.findByEducationId(id);
    }

    @GetMapping("/findByEducationIdOrderBySchoolGraduationDate")
    public ResponseEntity<DataResult<List<EducationInformationForCv>>> findByEducationIdOrderBySchoolGraduationDate(@RequestParam int id, @RequestParam Sort.Direction d) {
        DataResult<List<EducationInformationForCv>> result = educationInformationService.findByEducationIdOrderBySchoolGraduationDateDesc(id, d);
        return ResponseEntity.ok(result);
    }
}
