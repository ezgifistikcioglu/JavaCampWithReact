package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.CVService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.dtos.CvDetailForJobSeekerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cvs")
@CrossOrigin
public class CVsController {
    private final CVService cvService;

    @Autowired
    public CVsController(CVService cvService) {
        super();
        this.cvService = cvService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Cv>>> getAll() {
        DataResult<List<Cv>> result = cvService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Cv cv) {
        return ResponseEntity.ok(this.cvService.add(cv));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.cvService.delete(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody Cv cv) {
        return ResponseEntity.ok(this.cvService.update(cv));
    }

    @GetMapping("/getByCvId/{id}")
    public ResponseEntity<DataResult<Cv>> getByCvId(@PathVariable("id") int id) {
        DataResult<Cv> result = cvService.getByCvId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getCvDetailForJobSeekerById/{id}")
    public DataResult<CvDetailForJobSeekerDto> getCvDetailForJobSeekerById(@PathVariable("id") int id) {
        return this.cvService.getCvDetailForJobSeekerById(id);
    }
}
