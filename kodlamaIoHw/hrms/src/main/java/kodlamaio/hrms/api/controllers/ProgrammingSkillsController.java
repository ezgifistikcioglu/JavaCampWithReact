package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/programming-skills")
@CrossOrigin
public class ProgrammingSkillsController {
    private final ProgrammingSkillService programmingSkillService;

    @Autowired
    public ProgrammingSkillsController(ProgrammingSkillService programmingSkillService) {
        this.programmingSkillService = programmingSkillService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<ProgrammingSkillForCv>>> getAll() {
        DataResult<List<ProgrammingSkillForCv>> result = programmingSkillService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid ProgrammingSkillForCv cv) {
        return ResponseEntity.ok(this.programmingSkillService.add(cv));
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody @Valid ProgrammingSkillForCv cv) {
        return ResponseEntity.ok(this.programmingSkillService.delete(cv));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody @Valid ProgrammingSkillForCv cv) {
        return ResponseEntity.ok(this.programmingSkillService.update(cv));
    }

    @GetMapping("/findAllByCvId")
    public ResponseEntity<DataResult<List<ProgrammingSkillForCv>>> findAllByCvId(@RequestParam int id) {
        DataResult<List<ProgrammingSkillForCv>> result = programmingSkillService.findAllByCvId(id);
        return ResponseEntity.ok(result);
    }
}
