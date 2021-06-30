package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.WorkExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EducationInformationForCv;
import kodlamaio.hrms.entities.concretes.WorkExperienceForCv;
import kodlamaio.hrms.entities.dtos.WorkExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-experiences")
@CrossOrigin
public class WorkExperiencesController {
    private final WorkExperienceService workExperienceService;

    @Autowired
    public WorkExperiencesController(WorkExperienceService workExperienceService) {
        super();
        this.workExperienceService = workExperienceService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<WorkExperienceForCv>>> getAll() {
        DataResult<List<WorkExperienceForCv>> result = workExperienceService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody WorkExperienceDto experienceDto) {
        return ResponseEntity.ok(this.workExperienceService.add(experienceDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.workExperienceService.delete(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody WorkExperienceDto experienceDto) {
        return ResponseEntity.ok(this.workExperienceService.update(experienceDto));
    }

    @GetMapping("/findByExperienceId/{id}")
    public DataResult<List<WorkExperienceForCv>> findByExperienceId(@PathVariable("id") int id) {
        return this.workExperienceService.findByExperienceId(id);
    }

    @GetMapping("/findByExperienceIdOrderByBusinessLeavingDateDesc")
    public ResponseEntity<DataResult<List<WorkExperienceForCv>>> findByExperienceIdOrderByBusinessLeavingDateDesc(@RequestParam int id, @RequestParam Sort.Direction d) {
        DataResult<List<WorkExperienceForCv>> result = workExperienceService.findByExperienceIdOrderByBusinessLeavingDateDesc(id, d);
        return ResponseEntity.ok(result);
    }
}
