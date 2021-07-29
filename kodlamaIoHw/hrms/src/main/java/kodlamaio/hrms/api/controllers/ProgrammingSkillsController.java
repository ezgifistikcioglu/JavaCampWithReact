package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;
import kodlamaio.hrms.entities.dtos.ProgrammingSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/programming-skills")
@CrossOrigin
public class ProgrammingSkillsController {
    @Autowired
    private ProgrammingSkillService programmingSkillService;

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<ProgrammingSkillForCv>>> getAll() {
        DataResult<List<ProgrammingSkillForCv>> result = programmingSkillService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid ProgrammingSkillDto programmingSkillDto) {
        return ResponseEntity.ok(this.programmingSkillService.add(programmingSkillDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.programmingSkillService.delete(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@RequestBody ProgrammingSkillDto programmingSkillDto) {
        return ResponseEntity.ok(this.programmingSkillService.update(programmingSkillDto));
    }

    @GetMapping("/findAllByCvId/{id}")
    public ResponseEntity<DataResult<List<ProgrammingSkillForCv>>> findAllByCvId(@PathVariable("id") int id) {
        DataResult<List<ProgrammingSkillForCv>> result = programmingSkillService.findAllByCvId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByCvId/{id}")
    public DataResult<List<ProgrammingSkillForCv>> getByCvId(@PathVariable("id") int id) {
        return this.programmingSkillService.getByCv_CvId(id);
    }
}
