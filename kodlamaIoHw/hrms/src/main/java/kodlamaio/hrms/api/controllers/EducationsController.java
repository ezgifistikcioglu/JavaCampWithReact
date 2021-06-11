package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.EducationInformationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EducationInformationForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/educations")
public class EducationsController {
    private final EducationInformationService educationInformationService;

    @Autowired
    public EducationsController(EducationInformationService educationInformationService) {
        super();
        this.educationInformationService = educationInformationService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<EducationInformationForCv>>> getAll() {
        DataResult<List<EducationInformationForCv>> result = educationInformationService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid EducationInformationForCv information) {
        return ResponseEntity.ok(this.educationInformationService.add(information));
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody @Valid EducationInformationForCv information) {
        return ResponseEntity.ok(this.educationInformationService.delete(information));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody @Valid EducationInformationForCv information) {
        return ResponseEntity.ok(this.educationInformationService.update(information));
    }

    @GetMapping("/findByEducationIdOrderBySchoolGraduationDate")
    public ResponseEntity<DataResult<List<EducationInformationForCv>>> findByEducationIdOrderBySchoolGraduationDate(@RequestParam int id, @RequestParam Sort.Direction d) {
        DataResult<List<EducationInformationForCv>> result = educationInformationService.findByEducationIdOrderBySchoolGraduationDateDesc(id, d);
        return ResponseEntity.ok(result);
    }
}
