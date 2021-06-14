package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.WorkExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.WorkExperienceForCv;
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

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<WorkExperienceForCv>>> getAll() {
        DataResult<List<WorkExperienceForCv>> result = workExperienceService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody WorkExperienceForCv cv) {
        return ResponseEntity.ok(this.workExperienceService.add(cv));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody int cv) {
        return ResponseEntity.ok(this.workExperienceService.delete(cv));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody WorkExperienceForCv cv) {
        return ResponseEntity.ok(this.workExperienceService.update(cv));
    }

    @GetMapping("/findByExperienceIdOrderByBusinessLeavingDateDesc")
    public ResponseEntity<DataResult<List<WorkExperienceForCv>>> findByExperienceIdOrderByBusinessLeavingDateDesc(@RequestParam int id, @RequestParam Sort.Direction d) {
        DataResult<List<WorkExperienceForCv>> result = workExperienceService.findByExperienceIdOrderByBusinessLeavingDateDesc(id, d);
        return ResponseEntity.ok(result);
    }
}
